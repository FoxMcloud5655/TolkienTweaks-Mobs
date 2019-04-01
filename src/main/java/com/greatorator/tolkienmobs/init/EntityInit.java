package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.ambient.*;
import com.greatorator.tolkienmobs.entity.ammo.EntityBoulder;
import com.greatorator.tolkienmobs.entity.ammo.EntityFellBeastFireball;
import com.greatorator.tolkienmobs.entity.boss.*;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMElementalGolem;
import com.greatorator.tolkienmobs.entity.special.EntityTMGreatEagle;
import com.greatorator.tolkienmobs.entity.special.EntityTMMithrilGolem;
import com.greatorator.tolkienmobs.entity.special.EntityTMNazgul;
import com.greatorator.tolkienmobs.entity.hostile.*;
import com.greatorator.tolkienmobs.entity.passive.*;
import com.greatorator.tolkienmobs.entity.special.EntityTMGollum;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit
{
    //TODO Look at adding Mimic and Minotaur
    public static void init() {

        LogHelperTTM.info("Adding all the various fauna to see!");
        /* Every entity in our mod has an ID (local to this mod) */
        int id = 1;

        if (TTMConfig.enableMonster) {
            /* Monsters */
            if (TTMConfig.enableBarrowWights) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "barrowwight"), EntityTMBarrowWight.class, "barrow_wight", id++, TolkienMobs.instance, 64, 3, true, 0xFF947C, 0x5C1731);
            }
            if (TTMConfig.enableCaveTrolls) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "cavetroll"), EntityTMTroll.class, "cave_troll", id++, TolkienMobs.instance, 64, 3, true, 0xFF947C, 0xF0C55B);
            }
            if (TTMConfig.enableGoblins) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "goblin"), EntityTMGoblin.class, "goblin", id++, TolkienMobs.instance, 64, 3, true, 0xFF947C, 0x285D2C);
            }
            if (TTMConfig.enableHurons) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "huron"), EntityTMHuron.class, "huron", id++, TolkienMobs.instance, 64, 3, true, 0xFF947C, 0xC11A02);
            }
            if (TTMConfig.enableMirkwoodSpiders) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mirkwoodspider"), EntityTMMirkwoodSpider.class, "mirkwood_spider", id++, TolkienMobs.instance, 64, 3, true, 0xFF947C, 0xC07044);
            }
            if (TTMConfig.enableMordorOrcs) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mordororc"), EntityTMMordorOrc.class, "mordor_orc", id++, TolkienMobs.instance, 64, 3, true, 0xFF947C, 0x140F4E);
            }
            if (TTMConfig.enableTreeEnts) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "treeent"), EntityTMTreeEnt.class, "tree_ent", id++, TolkienMobs.instance, 64, 3, true, 0xFF947C, 0x2AEB7D);
            }
            if (TTMConfig.enableUrukhai) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "urukhai"), EntityTMUrukHai.class, "uruk_hai", id++, TolkienMobs.instance, 64, 3, true, 0xFF947C, 0xFEF9F2);
            }
            if (TTMConfig.enableWargs) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "warg"), EntityTMWarg.class, "warg", id++, TolkienMobs.instance, 64, 3, true, 0xFF947C, 0x492CCE);
            }
            if (TTMConfig.enableOathbreaker) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "oathbreaker"), EntityTMOathbreaker.class, "oathbreaker", id++, TolkienMobs.instance, 64, 3, true, 0xFF947C, 0x97ABFF);
            }
            if (TTMConfig.enableMimic) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mimicchest"), EntityTMMimicChest.class, "mimicchest", id++, TolkienMobs.instance, 64, 3, true, 0xFF947C, 0x3A7FFE);
            }
            if (TTMConfig.enableMinotaur) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "minotaur"), EntityTMMinotaur.class, "minotaur", id++, TolkienMobs.instance, 64, 3, true, 0xFF947C, 0x300FFE);
            }
            if (TTMConfig.enableBrigand) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "tmbrigand"), EntityTMBrigand.class, "tmbrigand", id++, TolkienMobs.instance, 64, 3, true, 0xFF947C, 0x078455);
            }
            if (TTMConfig.enableFellSpirit) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "fellspirit"), EntityTMFellSpirit.class, "fellspirit", id++, TolkienMobs.instance, 64, 3, true, 0xFF947C, 0x37B9D9);
            }
            if (TTMConfig.enableElementalGolem) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "elementalgolem"), EntityTMElementalGolem.class, "elementalgolem", id++, TolkienMobs.instance, 64, 3, true, 0xFF947C, 0x40B8B4);
            }
        }

        if (TTMConfig.enableBoss) {
            /* Bosses */
            if (TTMConfig.enableBalrog) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "balrog"), EntityTMBalrog.class, "balrog", id++, TolkienMobs.instance, 64, 3, true, 0xFB3366, 0x5C1731);
            }
            if (TTMConfig.enableFellBeast) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "fellbeast"), EntityTMFellBeast.class, "fellbeast", id++, TolkienMobs.instance, 64, 3, true, 0xFB3366, 0xF0C55B);
            }
            if (TTMConfig.enableWitchKing) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "witchking"), EntityTMWitchKing.class, "witchking", id++, TolkienMobs.instance, 64, 3, true, 0xFB3366, 0x285D2C);
            }
            if (TTMConfig.enableGwaihir) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "gwaihir"), EntityTMGwaihir.class, "gwaihir", id++, TolkienMobs.instance, 64, 3, true, 0xFB3366, 0xC11A02);
            }
            if (TTMConfig.enableMorgulGolem) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "elementalgolem7"), EntityTMMorgulGolem.class, "elementalgolem7", id++, TolkienMobs.instance, 64, 3, true, 0xFB3366, 0xC07044);
            }
        }

        if (TTMConfig.enableSpecial) {
            /* Special Mobs */
            if (TTMConfig.enableGollum) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "gollum"), EntityTMGollum.class, "gollum", id++, TolkienMobs.instance, 64, 3, true, 0xE5F50F, 0x5C1731);
            }
            if (TTMConfig.enableNazgul) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "nazgul"), EntityTMNazgul.class, "nazgul", id++, TolkienMobs.instance, 64, 3, true, 0xE5F50F, 0xF0C55B);
            }
            if (TTMConfig.enableGreatEagle) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "greateagle"), EntityTMGreatEagle.class, "greateagle", id++, TolkienMobs.instance, 64, 3, true, 0xE5F50F, 0x285D2C);
            }
            if (TTMConfig.enableMithrilGolem) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "elementalgolem6"), EntityTMMithrilGolem.class, "elementalgolem6", id++, TolkienMobs.instance, 64, 3, true, 0xE5F50F, 0xC11A02);
            }
        }

        if (TTMConfig.enablePassive) {
            /* Passive */
            if (TTMConfig.enableAuroch) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "auroch"), EntityTMAuroch.class, "auroch", id++, TolkienMobs.instance, 64, 3, true, 0x58DA84, 0x5C1731);
            }
            if (TTMConfig.enableDwarves) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "dwarf"), EntityTMDwarf.class, "dwarf", id++, TolkienMobs.instance, 64, 3, true, 0x58DA84, 0xF0C55B);
            }
            if (TTMConfig.enableElves) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "elves"), EntityTMElves.class, "elves", id++, TolkienMobs.instance, 64, 3, true, 0x58DA84, 0x285D2C);
            }
            if (TTMConfig.enableGoats) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "goat"), EntityTMGoat.class, "goat", id++, TolkienMobs.instance, 64, 3, true, 0x58DA84, 0xC11A02);
            }
            if (TTMConfig.enableHobbits) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "hobbit"), EntityTMHobbit.class, "hobbit", id++, TolkienMobs.instance, 64, 3, true, 0x58DA84, 0xC07044);
            }
            if (TTMConfig.enableHumans) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "human"), EntityTMHuman.class, "human", id++, TolkienMobs.instance, 64, 3, true, 0x58DA84, 0x140F4E);
            }
            if (TTMConfig.enableMumakil) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "mumakil"), EntityTMMumakil.class, "mumakil", id++, TolkienMobs.instance, 64, 3, true, 0x58DA84, 0x2AEB7D);
            }
        }

        if (TTMConfig.enableAmbient) {
            /* Ambient Mobs */
            if (TTMConfig.enableFrogs) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "toaddle"), EntityTMToad.class, "toaddle", id++, TolkienMobs.instance, 64, 3, true, 0xB7D8F8, 0x5C1731);
            }
            if (TTMConfig.enableSquirrels) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "sosquirrel"), EntityTMSquirrel.class, "sosquirrel", id++, TolkienMobs.instance, 64, 3, true, 0xB7D8F8, 0xF0C55B);
            }
            if (TTMConfig.enableThrush) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "thrush"), EntityTMThrush.class, "thrush", id++, TolkienMobs.instance, 64, 3, true, 0xB7D8F8, 0x285D2C);
            }
            if (TTMConfig.enableMidgeFlies) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "midgefly"), EntityTMMidgeFly.class, "midgefly", id++, TolkienMobs.instance, 64, 3, true, 0xB7D8F8, 0xC11A02);
            }
            if (TTMConfig.enableCrebain) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "crebain"), EntityTMCrebain.class, "crebain", id++, TolkienMobs.instance, 64, 3, true, 0xB7D8F8, 0xC07044);
            }
            if (TTMConfig.enableRats) {
                EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "tmrat"), EntityTMRat.class, "tmrat", id++, TolkienMobs.instance, 64, 3, true, 0xB7D8F8, 0x140F4E);
            }
        }

        /* Non-mob Entities */
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "boulder"), EntityBoulder.class, "ammo_boulder", id++, TolkienMobs.instance, 64, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(TolkienMobs.MODID, "fellbeast"), EntityFellBeastFireball.class, "fellbeast_fireball", id++, TolkienMobs.instance, 64, 3, true);

        /* If we want our mobs to spawn naturally. */
        if (TTMConfig.enableNaturalSpawn) {
            if (TTMConfig.enableMonster) {
                /* Monsters */
                if (TTMConfig.enableHurons) {
                    EntityRegistry.addSpawn(EntityTMHuron.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.FOREST, Biomes.FOREST_HILLS);
                }
                if (TTMConfig.enableTreeEnts) {
                    EntityRegistry.addSpawn(EntityTMTreeEnt.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.ROOFED_FOREST);
                }
                if (TTMConfig.enableMirkwoodSpiders) {
                    EntityRegistry.addSpawn(EntityTMMirkwoodSpider.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.ROOFED_FOREST);
                }
                if (TTMConfig.enableGoblins) {
                    EntityRegistry.addSpawn(EntityTMGoblin.class, 12, 2, 1, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableMordorOrcs) {
                    EntityRegistry.addSpawn(EntityTMMordorOrc.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
                }
                if (TTMConfig.enableUrukhai) {
                    EntityRegistry.addSpawn(EntityTMUrukHai.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
                }
                if (TTMConfig.enableWargs) {
                    EntityRegistry.addSpawn(EntityTMWarg.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
                }
                if (TTMConfig.enableCaveTrolls) {
                    EntityRegistry.addSpawn(EntityTMTroll.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableBarrowWights) {
                    EntityRegistry.addSpawn(EntityTMBarrowWight.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.ROOFED_FOREST);
                }
                if (TTMConfig.enableFellSpirit) {
                    EntityRegistry.addSpawn(EntityTMFellSpirit.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.SWAMPLAND);
                }
                if (TTMConfig.enableOathbreaker) {
                    EntityRegistry.addSpawn(EntityTMOathbreaker.class, 12, 1, 1, EnumCreatureType.AMBIENT, Biomes.COLD_TAIGA);
                }
                if (TTMConfig.enableMinotaur) {
                    EntityRegistry.addSpawn(EntityTMMinotaur.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableMimic) {
                    EntityRegistry.addSpawn(EntityTMMimicChest.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.DESERT_HILLS);
                }
                if (TTMConfig.enableBrigand) {
                    EntityRegistry.addSpawn(EntityTMBrigand.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS,Biomes.FOREST);
                }
                if (TTMConfig.enableElementalGolem) {
                    EntityRegistry.addSpawn(EntityTMElementalGolem.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.TAIGA,Biomes.DESERT,Biomes.EXTREME_HILLS,Biomes.BEACH,Biomes.PLAINS);
                }
            }

            if (TTMConfig.enablePassive) {
                /* Passive */
                if (TTMConfig.enableAuroch) {
                    EntityRegistry.addSpawn(EntityTMAuroch.class, 12, 2, 4, EnumCreatureType.CREATURE, Biomes.PLAINS);
                }
                if (TTMConfig.enableGoats) {
                    EntityRegistry.addSpawn(EntityTMGoat.class, 12, 2, 4, EnumCreatureType.CREATURE, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableMumakil) {
                    EntityRegistry.addSpawn(EntityTMMumakil.class, 12, 1, 1, EnumCreatureType.CREATURE, Biomes.DESERT);
                }
            }

            if (TTMConfig.enableAmbient) {
                /* Ambient */
                if (TTMConfig.enableThrush) {
                    EntityRegistry.addSpawn(EntityTMThrush.class, 12, 1, 1, EnumCreatureType.CREATURE, Biomes.COLD_TAIGA);
                }
                if (TTMConfig.enableSquirrels) {
                    EntityRegistry.addSpawn(EntityTMSquirrel.class, 12, 1, 1, EnumCreatureType.CREATURE, Biomes.FOREST);
                }
                if (TTMConfig.enableMidgeFlies) {
                    EntityRegistry.addSpawn(EntityTMMidgeFly.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.SWAMPLAND);
                }
                if (TTMConfig.enableCrebain) {
                    EntityRegistry.addSpawn(EntityTMCrebain.class, 12, 1, 1, EnumCreatureType.MONSTER, Biomes.EXTREME_HILLS);
                }
                if (TTMConfig.enableFrogs) {
                    EntityRegistry.addSpawn(EntityTMToad.class, 12, 1, 1, EnumCreatureType.WATER_CREATURE, Biomes.PLAINS);
                }
                if (TTMConfig.enableRats) {
                    EntityRegistry.addSpawn(EntityTMRat.class, 12, 1, 1, EnumCreatureType.AMBIENT, Biomes.ROOFED_FOREST);
                }
            }
        }

        LogHelperTTM.info("I chose you mobi-chu!");
    }
}