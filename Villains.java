import java.util.Random;

public class Villains extends Characters{

    int newXp;
    public String[] VillainType = {"Spirit", "Dragon", "ExoSkeleton"};
    int typePointer = 0;
    int Damage ;
    double Ratio;
    int Defense ;
    int DodgeChance ;

    public Villains(String Name, int XP,int damage , int defense , int dodgeChance) {
        super(Name,100,100,XP);
//        this.newXp = XP;

        this.newXp = XP;
        this.Damage = damage;
        this.Defense = defense;
        this.DodgeChance = dodgeChance;
        this.Ratio=0.04;


    }

    @Override
    public int Attack() {
        return (int) (Math.random()*(XP/4 + 1+Damage*Ratio)+ XP/4+3+Damage*(Ratio/2));
    }
    public Villains(){

    }
    @Override
    public int Defend() {
        return (int) (Math.random()*(XP/4 + 1+(Defense+DodgeChance)*(Ratio+0.02))+ XP/4+3+(Defense+DodgeChance)*0.5*Ratio);
    }
    public double RatioTaken(int modeChoice){
        Ratio = 0.02;
        if(modeChoice == 1){
            Ratio = 0.02;
            return Ratio;
        }
       else if(modeChoice == 1){
            Ratio = 0.08;
            return Ratio;
        }
       else if(modeChoice == 1){
            Ratio = 0.09;
            return Ratio;


        }
return Ratio;
    }
    public Object chooseType(){
        Random a = new Random();
        Villains enemy = new Villains();
        int Choice = a.nextInt(3)+1;
        int b = Choice;
        if(Choice == 1){
            enemy = (Villains) chooseDragons();
        }
        else if(Choice == 2){
            enemy = (Villains) chooseSpirits();
        }
        else if(Choice == 3){
            enemy = (Villains) chooseExoskeleton();
        }
        return enemy;
    }

    public Object chooseDragons(){
        Random a = new Random();
        Villains enemysublist = new Villains();
        int Choice = a.nextInt(12)+1;
        int b = Choice;
        if(Choice == 1){
             enemysublist= new Villains(DragonType[typePointer],3,300,400,35);
//            System.out.println(DragonType[typePointer]);
            return enemysublist;


        }
        else if(Choice == 2){
            enemysublist= new Villains(DragonType[typePointer+1],2,200,400,20);
//            System.out.println(DragonType[typePointer+1]);
            return enemysublist;


        }
        else if(Choice == 3){
            enemysublist= new Villains(DragonType[typePointer+2],4,400,500,45);
//            System.out.println(DragonType[typePointer+2]);
            return enemysublist;


        }
        else if(Choice == 4){
            enemysublist= new Villains(DragonType[typePointer+3],1,100,200,10);
//            System.out.println(DragonType[typePointer+3]);
            return enemysublist;


        }
        else if(Choice == 5){
            enemysublist= new Villains(DragonType[typePointer+4],7,700,600,75);
//            System.out.println(DragonType[typePointer+4]);
            return enemysublist;


        }
        else if(Choice == 6){
            enemysublist= new Villains(DragonType[typePointer+5],5,600,500,65);
//            System.out.println(DragonType[typePointer+5]);
            return enemysublist;


        }
        else if(Choice == 7){
            enemysublist= new Villains(DragonType[typePointer+6],10,1000,9000,55);
//            System.out.println(DragonType[typePointer+6]);
            return enemysublist;


        }
        else if(Choice == 8){
            enemysublist= new Villains(DragonType[typePointer+7],6,600,700,60);
//            System.out.println(DragonType[typePointer+7]);
            return enemysublist;


        }
        else if(Choice == 9){
            enemysublist= new Villains(DragonType[typePointer+8],9,900,950,85);
//            System.out.println(DragonType[typePointer+8]);
            return enemysublist;


        }
        else if(Choice == 10){
            enemysublist= new Villains(DragonType[typePointer+9],8,800,900,80);
//            System.out.println(DragonType[typePointer+9]);
            return enemysublist;


        }
        else if(Choice == 11){
            enemysublist= new Villains(DragonType[typePointer+10],6,600,400,60);
//            System.out.println(DragonType[typePointer+10]);
            return enemysublist;


        } else if(Choice == 12){
            enemysublist= new Villains(DragonType[typePointer+11],9,900,600,75);
//            System.out.println(DragonType[typePointer+11]);
            return enemysublist;


        }
        return enemysublist;

    }
    public String[] DragonType = {"Desghidorrah", "Chrysophylax", "BunsenBurner","Natsunomeryu","TheScaleless","Kas-Ethelinh","Alexstraszan","Phaarthurnax","D-Maleficent","TheWeatherbe","Igneel","BlueEyesWhite"};

    public Object chooseSpirits(){
        Random a = new Random();
        Villains enemysublist = new Villains();
        int Choice = a.nextInt(11)+1;
        int b = Choice;
        if(Choice == 1){
            enemysublist= new Villains(SpiritType[typePointer],2,600,500,40);
            return enemysublist;


        }
        else if(Choice == 2){
            enemysublist= new Villains(SpiritType[typePointer+1],1,450,350,35);
            return enemysublist;


        }
        else if(Choice == 3){
            enemysublist= new Villains(SpiritType[typePointer+2],3,550,450,25);
            return enemysublist;


        }
        else if(Choice == 4){
            enemysublist= new Villains(SpiritType[typePointer+3],4,700,600,40);
            return enemysublist;


        }
        else if(Choice == 5){
            enemysublist= new Villains(SpiritType[typePointer+4],5,800,700,50);
            return enemysublist;


        }
        else if(Choice == 6){
            enemysublist= new Villains(SpiritType[typePointer+5],6,950,450,35);
            return enemysublist;


        }
        else if(Choice == 7){
            enemysublist= new Villains(SpiritType[typePointer+6],7,350,150,75);
            return enemysublist;


        }
        else if(Choice == 8){
            enemysublist= new Villains(SpiritType[typePointer+7],8,600,900,20);
            return enemysublist;


        }
        else if(Choice == 9){
            enemysublist= new Villains(SpiritType[typePointer+8],9,550,600,35);
            return enemysublist;


        }
        else if(Choice == 10){
            enemysublist= new Villains(SpiritType[typePointer+9],10,300,200,50);
            return enemysublist;


        }
        else if(Choice == 11){
            enemysublist= new Villains(SpiritType[typePointer+10],1,100,100,50);
            return enemysublist;


        }
        return enemysublist;

    }
    public String[] SpiritType = {"Andrealphus", "Blinky", "Andromalius","Chiang-shih","FallenAngel","Ereshkigall","Melchiresas","Jormunngand","Rakkshasass","Taltecuhtli","Casper"};

    public Object chooseExoskeleton(){
        Random a = new Random();
        Villains enemysublist = new Villains();
        int Choice = a.nextInt(12)+1;
        int b = Choice;
        if(Choice == 1){
            enemysublist= new Villains(ExoskeletonType[typePointer],7,700,800,75);
            return enemysublist;


        }
        else if(Choice == 2){
            enemysublist= new Villains(ExoskeletonType[typePointer+1],3,350,450,30);
            return enemysublist;


        }
        else if(Choice == 3){
            enemysublist= new Villains(ExoskeletonType[typePointer+2],1,150,250,15);
            return enemysublist;


        }
        else if(Choice == 4){
            enemysublist= new Villains(ExoskeletonType[typePointer+3],2,250,350,25);
            return enemysublist;


        }
        else if(Choice == 5){
            enemysublist= new Villains(ExoskeletonType[typePointer+4],4,400,500,45);
            return enemysublist;


        }
        else if(Choice == 6){
            enemysublist= new Villains(ExoskeletonType[typePointer+5],6,650,750,60);
            return enemysublist;


        }
        else if(Choice == 7){
            enemysublist= new Villains(ExoskeletonType[typePointer+6],8,850,950,85);
            return enemysublist;


        }
        else if(Choice == 8){
            enemysublist= new Villains(ExoskeletonType[typePointer+7],5,550,650,55);
            return enemysublist;


        }
        else if(Choice == 9){
            enemysublist= new Villains(ExoskeletonType[typePointer+8],10,1000,90,55);
            return enemysublist;


        }
        else if(Choice == 10){
            enemysublist= new Villains(ExoskeletonType[typePointer+9],9,950,850,90);
            return enemysublist;


        }
        else if(Choice == 11){
            enemysublist= new Villains(ExoskeletonType[typePointer+10],6,600,600,55);
            return enemysublist;


        } else if(Choice == 12){
            enemysublist= new Villains(ExoskeletonType[typePointer+11],10,1000,1000,50);
            return enemysublist;


        }
        return enemysublist;

    }
    public String[] ExoskeletonType = {"Cyrrollalee", "Brandobaris", "BigBad-Wolf","WickedWitch","Aasterinian","Chronepsish","Kiaransalee","St-Shargaas","Merrshaullk","St-Yeenoghu","DocOck","Exodia"};

}
