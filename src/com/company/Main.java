package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 1000;
    public static int bossDamage = 60;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {300, 270, 250, 200};
    public static int[] heroesDamages = {50, 70, 30};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "doc"};
    public static int roundCounter = 0;
    private static int i;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void bossAngryState() {
        if (bossHealth <= 200) {
            Random r = new Random();
            int healthRand = r.nextInt(31) + 20;
            int damageRand = r.nextInt(11) + 10;
            bossHealth = bossDamage + healthRand;
            bossDamage = bossDamage + damageRand;
            System.out.println("Boss became angry: " +
                    "increased health by " + healthRand
                    + " and damage " + damageRand);
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static void round() {
        roundCounter++;
        changeBossDefence();
        bossAngryState();
        bossHits();
        heroesHit();
        printStatistics();
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesHealth.length;i++);
        if (heroesHealth[i] >= 1 && heroesHealth[i] < 100);
        Random r = new Random();
        for (int i = 0; i < heroesDamages.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    r = new Random();
                    int randomHeal = r.nextInt(44);
                    heroesHealth[i]=heroesHealth[i]+randomHeal;
                    int coeff = r.nextInt(5) + 2; // 2,3,4,5,6
                    if (bossHealth - heroesDamages[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamages[i] * coeff;
                    }
                    System.out.println(heroesAttackType[i] +
                            " critical damage " + heroesDamages[i] * coeff);
                } else {
                    if (bossHealth - heroesDamages[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamages[i];
                    }
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("ROUND ----- " + roundCounter);
        System.out.println("______________");
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] +
                    " health: " + heroesHealth[i]);
        }
        System.out.println("______________");
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }

        return allHeroesDead;
    }
}

