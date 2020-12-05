# GalacticLib #
In order to use this plugin properly you must also use MassiveCore for all Engine, Command, Integrations, etc. and your plugin should be purely framed around MassiveCore.

## Sample pom.xml ##
If you'd like to use GalacticLib below is some example pom.xml formats to use when your plugin has an API and doesn't have an API.

### No API ###
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.stellardev.galactic</groupId>
    <artifactId>plugin-name-here</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <plugin.package>org.stellardev.plugin-name-here</plugin.package>
    </properties>

    <build>
        <finalName>${project.artifactId}</finalName>
        <defaultGoal>clean package install</defaultGoal>
        <sourceDirectory>src</sourceDirectory>
        <plugins>
            <plugin>
                <version>3.1</version>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <createDependencyReducedPom>false</createDependencyReducedPom>
                    <filters>
                        <filter>
                            <artifact>*:*</artifact>
                            <excludes>
                                <exclude>META-INF/*.MF</exclude>
                            </excludes>
                        </filter>
                    </filters>
                    <relocations>
                        <relocation>
                            <pattern>stellar</pattern>
                            <shadedPattern>${plugin.package}.stellar</shadedPattern>
                        </relocation>
                    </relocations>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-antrun-plugin</artifactId>
                <version>1.8</version>
                <executions>
                    <execution>
                        <phase>install</phase>
                        <configuration>
                            <target>
                                <copy file="target\${build.finalName}.jar" tofile="..\..\..\aaPlugins\GalacticSeries\${build.finalName}.jar"/>
                            </target>
                        </configuration>
                        <goals>
                            <goal>run</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
        <resources>
            <resource>
                <directory>resources</directory>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>

    <dependencies>
        <dependency> <!-- PROVIDED -->
            <groupId>org.stellardev.galactic</groupId>
            <artifactId>GalacticLib</artifactId>
            <version>LATEST</version>
            <scope>provided</scope>
        </dependency>
        <dependency> <!-- COMPILE -->
            <groupId>org.stellardev.galactic</groupId>
            <artifactId>StellarLicense</artifactId>
            <version>LATEST</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>
</project>
```

### With API ###
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.stellardev.galactic</groupId>
    <artifactId>plugin-name-here</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <plugin.package>org.stellardev.plugin-name-here</plugin.package>
        <plugin.path>org/stellardev/plugin-name-here/</plugin.path>
    </properties>

    <profiles>
        <profile>
            <id>default</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <build>
                <finalName>${project.artifactId}</finalName>
                <plugins>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-shade-plugin</artifactId>
                        <executions>
                            <execution>
                                <phase>package</phase>
                                <goals>
                                    <goal>shade</goal>
                                </goals>
                            </execution>
                        </executions>
                        <configuration>
                            <createDependencyReducedPom>false</createDependencyReducedPom>
                            <filters>
                                <filter>
                                    <artifact>*:*</artifact>
                                    <excludes>
                                        <exclude>META-INF/*.MF</exclude>
                                    </excludes>
                                </filter>
                            </filters>
                            <relocations>
                                <relocation>
                                    <pattern>stellar</pattern>
                                    <shadedPattern>${plugin.package}.stellar</shadedPattern>
                                </relocation>
                            </relocations>
                        </configuration>
                    </plugin>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-antrun-plugin</artifactId>
                        <version>1.8</version>
                        <executions>
                            <execution>
                                <phase>install</phase>
                                <configuration>
                                    <target>
                                        <copy file="target\${build.finalName}.jar" tofile="..\..\..\aaPlugins\GalacticSeries\${build.finalName}.jar"/>
                                    </target>
                                </configuration>
                                <goals>
                                    <goal>run</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
            </build>
        </profile>
        <profile>
            <id>api</id>
            <build>
                <finalName>${project.artifactId}-API</finalName>
                <pluginManagement>
                    <plugins>
                        <plugin>
                            <groupId>org.apache.maven.plugins</groupId>
                            <artifactId>maven-jar-plugin</artifactId>
                            <version>3.0.2</version>
                            <configuration>
                                <includes>
                                    <include>${plugin.path}api/**</include>
                                    <include>${plugin.path}event/**</include>
                                    <include>${plugin.path}object/**</include>
                                </includes>
                                <archive>
                                    <addMavenDescriptor>false</addMavenDescriptor>
                                </archive>
                            </configuration>
                        </plugin>
                    </plugins>
                </pluginManagement>
            </build>
        </profile>
    </profiles>

    <build>
        <defaultGoal>clean package install</defaultGoal>
        <sourceDirectory>src</sourceDirectory>
        <resources>
            <resource>
                <directory>resources</directory>
                <filtering>true</filtering>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <version>3.1</version>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-antrun-plugin</artifactId>
                <version>1.8</version>
                <executions>
                    <execution>
                        <phase>install</phase>
                        <configuration>
                            <target>
                                <copy file="target\${build.finalName}.jar" tofile="..\..\..\aaPlugins\GalacticSeries\${build.finalName}.jar"/>
                            </target>
                        </configuration>
                        <goals>
                            <goal>run</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

    <dependencies>
        <dependency> <!-- PROVIDED -->
            <groupId>org.stellardev.galactic</groupId>
            <artifactId>GalacticLib</artifactId>
            <version>LATEST</version>
            <scope>provided</scope>
        </dependency>
        <dependency> <!-- COMPILE -->
            <groupId>org.stellardev.galactic</groupId>
            <artifactId>StellarLicense</artifactId>
            <version>LATEST</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>
</project>
```
