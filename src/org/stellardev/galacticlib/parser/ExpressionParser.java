package org.stellardev.galacticlib.parser;

// @Credit: Boann on StackOverflow
// @Source: https://stackoverflow.com/questions/3422673/how-to-evaluate-a-math-expression-given-in-string-form
public class ExpressionParser {

    private final String expression;
    private int pos = -1, ch;

    public ExpressionParser(String input) {
        this.expression = input;
    }

    public double parse() {
        nextChar();
        double x = parseExpression();
        if (this.pos < this.expression.length()) throw new RuntimeException("Unexpected: " + (char) this.ch);
        return x;
    }

    private void nextChar() {
        this.ch = (++this.pos < this.expression.length()) ? this.expression.charAt(this.pos) : -1;
    }

    private boolean eat(int charToEat) {
        while (this.ch == ' ') nextChar();
        if (this.ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    // Grammar:
    // expression = term | expression `+` term | expression `-` term
    // term = factor | term `*` factor | term `/` factor
    // factor = `+` factor | `-` factor | `(` expression `)`
    //        | number | functionName factor | factor `^` factor
    private double parseExpression() {
        double x = parseTerm();
        for (;;) {
            if      (eat('+')) x += parseTerm(); // addition
            else if (eat('-')) x -= parseTerm(); // subtraction
            else return x;
        }
    }

    private double parseTerm() {
        double x = parseFactor();
        for (;;) {
            if      (eat('*')) x *= parseFactor(); // multiplication
            else if (eat('/')) x /= parseFactor(); // division
            else return x;
        }
    }

    private double parseFactor() {
        if (eat('+')) return parseFactor(); // unary plus
        if (eat('-')) return -parseFactor(); // unary minus

        double x;

        int startPos = this.pos;
        if (eat('(')) { // parentheses
            x = parseExpression();
            eat(')');
        } else if ((this.ch >= '0' && this.ch <= '9') || this.ch == '.') { // numbers
            while ((this.ch >= '0' && this.ch <= '9') || this.ch == '.') nextChar();
            x = Double.parseDouble(this.expression.substring(startPos, this.pos));
        } else if (this.ch >= 'a' && this.ch <= 'z') { // functions
            while (this.ch >= 'a' && this.ch <= 'z') nextChar();
            String func = this.expression.substring(startPos, this.pos);
            x = parseFactor();

            switch (func) {
                case "sqrt":
                    x = Math.sqrt(x);
                    break;
                case "sin":
                    x = Math.sin(Math.toRadians(x));
                    break;
                case "cos":
                    x = Math.cos(Math.toRadians(x));
                    break;
                case "tan":
                    x = Math.tan(Math.toRadians(x));
                    break;
                default:
                    throw new RuntimeException("Unknown function: " + func);
            }
        } else {
            throw new RuntimeException("Unexpected: " + (char)ch);
        }

        if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

        return x;
    }

}
