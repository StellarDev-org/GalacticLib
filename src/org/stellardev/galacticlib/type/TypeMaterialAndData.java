package org.stellardev.galacticlib.type;

import com.massivecraft.massivecore.MassiveCoreMConf;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.TypeAbstract;
import com.massivecraft.massivecore.util.Txt;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.stellardev.galacticlib.entity.Conf;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TypeMaterialAndData extends TypeAbstract<TypeMaterialAndData.MaterialAndData> {

    private static final TypeMaterialAndData instance = new TypeMaterialAndData();
    public static TypeMaterialAndData get() { return instance; }

    private static final Map<String, Material> EXTRA_MATERIAL_CHECKS = new HashMap<>();

    private TypeMaterialAndData() {
        super(TypeMaterialAndData.class);
    }

    @Override
    public MaterialAndData read(String arg, CommandSender sender) throws MassiveException {
        Material material;
        short data = 0;

        if(arg.contains(":")) {
            String[] split = arg.split(":");
            String materialInput = split[0];
            String dataInput = split[1];

            material = attemptMaterial(materialInput);

            try {
                data = Short.parseShort(dataInput);
            } catch (Exception ignore) {}
        } else {
            material = attemptMaterial(arg);
        }

        if(material == null) throw new MassiveException().addMsg(Conf.get().msgInvalidMaterial);

        return new MaterialAndData(material, data);
    }

    @Override
    public Collection<String> getTabList(CommandSender sender, String arg) {
        return Arrays.stream(Material.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    private Material attemptMaterial(String input) {
        Material material;

        try {
            material = Material.valueOf(input.toUpperCase());
        } catch (Exception ex) {
            material = null;
        }

        if(material != null) return material;

        try {
            material = Material.matchMaterial(input);
        } catch (Exception ex) {
            material = null;
        }

        if(material != null) return material;

        if(EXTRA_MATERIAL_CHECKS.isEmpty()) {
            fillExtraMaterialChecks();
        }

        if(EXTRA_MATERIAL_CHECKS.containsKey(input.toLowerCase())) {
            return EXTRA_MATERIAL_CHECKS.get(input.toLowerCase());
        }

        return null;
    }

    private void fillExtraMaterialChecks() {
        for(Material material : Material.values()) {
            EXTRA_MATERIAL_CHECKS.put(material.name().toLowerCase().replace("_", ""), material);
        }
    }

    public static class MaterialAndData {

        private final Material material;
        private final short data;

        public MaterialAndData(Material material, short data) {
            this.material = material;
            this.data = data;
        }

        public Material getMaterial() {
            return this.material;
        }

        public short getData() {
            return this.data;
        }

        public String getDisplayName() {
            if(getData() == 0) {
                return Txt.getNicedEnum(getMaterial());
            } else {
                return Txt.getNicedEnum(getMaterial()) + ":" + getData();
            }
        }
    }

}
