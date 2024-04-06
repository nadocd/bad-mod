package mod;

import arc.graphics.Color;
import arc.util.Log;
import mindustry.content.Blocks;
import mindustry.content.TechTree;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.DrawFlame;
import mindustry.world.draw.DrawMulti;
import mindustry.world.meta.BuildVisibility;

public class BlocksJCM extends Blocks {

    public static Block
            niotiumSmelter;

    public static void load(){
        Log.info("Loading JCM blocks");

        UnusableTrash.loadWarnOnError(
                ()-> niotiumSmelter =new GenericCrafter("niotium-smelter"){{
                    consumePower(5);
                    size=3;
                    craftTime=30;
                    itemCapacity=10;
                    techNode=new TechTree.TechNode(
                            surgeSmelter.techNode,
                            this,
                            requirements
                    );
                    requirements=new ItemStack[]{
                            new ItemStack(ItemsJCM.thorium, 240),
                            new ItemStack(ItemsJCM.graphite, 150),
                            new ItemStack(ItemsJCM.metaglass, 75),
                            new ItemStack(ItemsJCM.surgeAlloy, 60)
                    };
                    category=Category.crafting;
                    outputItem=new ItemStack(ItemsJCM.niotium,1);
                    consumeItems(new ItemStack[]{
                            new ItemStack(ItemsJCM.thorium,3),
                            new ItemStack(ItemsJCM.titanium,3),
                            new ItemStack(ItemsJCM.graphite,2)
                    });
                    buildVisibility= BuildVisibility.shown;
                    drawer = new DrawMulti(new DrawFlame(Color.valueOf("ffef99")));
                }},
                "niotium-smelter");

        //Anuken plz add and fix DirectionalForceProjector(broken class!!!!! xd) to Mindustry
    }
}
