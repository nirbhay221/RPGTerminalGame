import javax.print.attribute.standard.NumberOfDocuments;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Logic {
    static Scanner input = new Scanner(System.in);
    static Heroes player;
    static Weapons weapons;
    static int modeChoice;
    static Armor armor;
    static ArrayList<Villains> EnemyList = new ArrayList<Villains>();
    public static ArrayList<Heroes> HeroList = new ArrayList<Heroes>() ;
    public static ArrayList<Villains> VillainList = new ArrayList<Villains>();
    static int NumberOfHeroes ;
    static int levelUp = 10;
    public static ArrayList<Heroes> FaintedHeroList = new ArrayList<>();
    public static ArrayList<Villains> DeadVillainList = new ArrayList();
    static int seriousAct = 5;
    static int RandomPrice = 10000;
    static int RandomDamage = 20000;
    static int RandomDamageReduction = 2000;
    static int RandomLevel = 5;
    static LightningSpells lightningSpells;
    static Potions potions;
    static FireSpells fireSpells;
    static int RandomManacost = 500;
    static IceSpells iceSpells;

    public static boolean isPacing;
    static boolean isDropped= false;
    public static Market market = new Market("Market",0,0,0,0,0);
    public static int place = 0 , act =1 ;
    public static int size= 8;
    public static String[] places = {"EverLasting Mountains" , "Landlines","WetLands ","Woods"};
    public static String[] Collision = {"Battle","Battle","Battle","Rest","Rest","Rest"};
    public static String[] enemies = {"Dragon","Minotaurs","Goblins","Spider","Foam"};
    static Board newBoard = new Board(size,size);
    static boolean isEquipped = false;


   public static void GenerateEnemies(int numberOfHeroes){
       Villains villain ;
       Villains villain2 ;
       Villains villain3 ;
       Villains villain4 ;
       Villains villain5 ;
       villain = new Villains();
       villain2 = new Villains();
       villain3 = new Villains();
       villain4 = new Villains();
       villain5 = new Villains();
       villain = (Villains) villain.chooseType();

       villain2 = (Villains) villain2.chooseType();

       villain3 = (Villains) villain3.chooseType();
       villain4 = (Villains) villain4.chooseType();
       villain5 = (Villains) villain5.chooseType();
        villain.Ratio=villain.RatioTaken(modeChoice);
       villain5.Ratio=villain.RatioTaken(modeChoice);
       villain2.Ratio=villain.RatioTaken(modeChoice);
       villain3.Ratio=villain.RatioTaken(modeChoice);
       villain4.Ratio=villain.RatioTaken(modeChoice);

       for(int i = 0 ; i < numberOfHeroes ; i++){
           EnemyList.add((Villains) villain.chooseType());
       }

//       EnemyList.add(villain);
//       EnemyList.add(villain2);
//       EnemyList.add(villain3);
//       EnemyList.add(villain4);
//       EnemyList.add(villain5);
       String[] newenemies =new String[EnemyList.size()];
     System.out.println("Villain Names : "+villain.CharacterName+", "+villain2.CharacterName+" ,"+villain3.CharacterName+" ,"+villain4.CharacterName+", "+villain5.CharacterName);
       for(int i = 0 ; i< EnemyList.size();i++){
       newenemies[i] = EnemyList.get(i).CharacterName;
       }
       enemies = newenemies;

   }
   public static void populateMarket(){
Random rnd =new Random();
String[] Items = {"Florentine Diamond","Copper Scroll Treasures","Gold","Elixir","Cloak of Invisibility","Cloak of Invincibility","Excalibur","Sword of Eden","Shattered Staff of Eden","Imperial Sceptre","Staff of Hermes Trismegistus","Scepter of Alexander the Great","Sword of Damokles","Mjölnir","Trident of Eden","Odin's Eye","Fountain of Youth","Trident of Eden","Apple of Eden"};
String[] LightningItems ={"Lightning bolt","Odin's Wrath","Lightning Breath", "Zeus Wrath","Lightning Lure","Lightning Ring","Lightning Storm","Thor's Thunder"};
String[] IceItems ={"Mass Ice Prism","Frost Bites","Frost Giant Summon", "Ice Spear","Mega Distract","Melt", "Colossus","BLight Hound"};
String[] FireItems = {"Burning Hands" , "Wall of Fire", "Fireball","Flaming Strike","Meteor Swarm","Burning Spray","Eternal Flame"};
String[] ArmorItems = {"Eternal Protection" , "Lion Shield", "Dragon Fur Shield","Protection Of the Sun","Orean's Guard Shield","Majestic Vapor Shield","Hermit Turtle Armor"};

market.WeaponMarket = weapons.MarketWeapons();
market.LightningSpellsMarket = lightningSpells.MarketLightningSpells();
market.FireSpellsMarket = fireSpells.MarketFireSpells();
market.IceSpellsMarket = iceSpells.MarketIceSpells();
market.ArmorMarket = armor.MarketArmor();
market.PotionsMarket = potions.MarketPotions();
int rndSelection = rnd.nextInt(5);
int rndPriceSelection = rnd.nextInt( RandomPrice);
int rndDamageSelection = rnd.nextInt( RandomDamage);
int rndLevelSelection = rnd.nextInt( RandomLevel);
int rndManaSelection = rnd.nextInt(RandomManacost);
int rndDamageReductionSelection = rnd.nextInt(RandomDamageReduction);
for(int i = 0 ; i < rndSelection;i++){
market.WeaponMarket.add(new Weapons(Items[i],rndPriceSelection,rndLevelSelection,rndDamageSelection,(i%2)+1));
}
for(int i = 0 ; i < rndSelection;i++){
           market.LightningSpellsMarket.add(new LightningSpells(LightningItems[i],rndPriceSelection,rndLevelSelection,rndDamageSelection,rndManaSelection));
       }

       for(int i = 0 ; i < rndSelection;i++){
           market.IceSpellsMarket.add(new IceSpells(IceItems[i],rndPriceSelection,rndLevelSelection,rndDamageSelection,rndManaSelection));
       }
       for(int i = 0 ; i < rndSelection;i++){
           market.FireSpellsMarket.add(new FireSpells(FireItems[i],rndPriceSelection,rndLevelSelection,rndDamageSelection,rndManaSelection));
       }
       for(int i = 0 ; i < rndSelection;i++){
           market.ArmorMarket.add(new Armor(ArmorItems[i],rndPriceSelection,rndLevelSelection,rndDamageReductionSelection));
       }

   }
   public static void printMarket(){
       System.out.println("Weapon Market :");
       for(int i = 0 ; i < market.WeaponMarket.size();i++){
           System.out.println("("+(i+1)+")"+market.WeaponMarket.get(i).Name+" Price"+market.WeaponMarket.get(i).price+" Damage Value"+market.WeaponMarket.get(i).damageValue+ " Number of Hands Required"+market.WeaponMarket.get(i).noHands+" Level"+market.WeaponMarket.get(i).level);
       }
       System.out.println("Lightning Spell Market :");
       for(int i = 0 ; i < market.LightningSpellsMarket.size();i++){
           System.out.println("("+(i+1)+")"+market.LightningSpellsMarket.get(i).Name+" Price : "+market.LightningSpellsMarket.get(i).price+" Level : "+market.LightningSpellsMarket.get(i).level+" Damage : "+market.LightningSpellsMarket.get(i).Damage+" MP Cost :"+market.LightningSpellsMarket.get(i).MPCost);
       }
       System.out.println("Fire Spell Market :");
       for(int i = 0 ; i < market.FireSpellsMarket.size();i++){
           System.out.println("("+(i+1)+")"+market.FireSpellsMarket.get(i).Name+" Price : "+market.FireSpellsMarket.get(i).price+" Level : "+market.FireSpellsMarket.get(i).level+" Damage : "+market.FireSpellsMarket.get(i).Damage+" MP Cost :"+market.FireSpellsMarket.get(i).MPCost);
       }
       System.out.println("Ice Spell Market :");
       for(int i = 0 ; i < market.IceSpellsMarket.size();i++){
           System.out.println("("+(i+1)+")"+market.IceSpellsMarket.get(i).Name+" Price : "+market.IceSpellsMarket.get(i).price+" Level : "+market.IceSpellsMarket.get(i).level+" Damage : "+market.IceSpellsMarket.get(i).Damage+" MP Cost :"+market.IceSpellsMarket.get(i).MPCost);
       }
       System.out.println("Armor Market :");
       for(int i = 0 ; i < market.ArmorMarket.size();i++){
           System.out.println("("+(i+1)+")"+market.ArmorMarket.get(i).Name+" Price"+market.ArmorMarket.get(i).price+" Level "+market.ArmorMarket.get(i).level+" Damage Reduction : "+market.ArmorMarket.get(i).damageReductionValue);
       }

   }
    public static int UserInput(String printStatement , int Choices) {
        System.out.println(printStatement);
        while(!input.hasNextInt()){
            System.out.println("Enter a valid integer");
            input.next();
        }
        int sc = input.nextInt();
        while (sc < 1 || sc > Choices) {
            System.out.println(printStatement);
            System.out.println("Enter the ideal choice mentioned");
            while(!input.hasNextInt()){
                System.out.println("Enter a valid integer");
                input.next();
            }
            sc = Integer.parseInt(input.next());


        }
        return sc;

    }
    public static void ClearTerminal(){
        for(int i = 0 ; i < 100 ; i++ ){
            System.out.println();
        }
    }
    public static void printSeparation(int n){
        for(int i = 0 ; i < 100 ; i++ ){
            System.out.print(" -- ");

        }  System.out.println();

    }
    public static void printHeader(String title ){
        printSeparation(30);
        System.out.println(title);
        printSeparation(30);
    }
    public static void TypeToContinue(){
        System.out.println("Enter the choice you wish to continue with :");
        input.next();
    }
    public static int WeaponMoneyReduced(int playerMoney,int WeaponCost){
       if(playerMoney>WeaponCost){
    playerMoney = playerMoney-WeaponCost;
       }
       else{
           playerMoney = playerMoney;
           System.out.println("You don't have enough money to buy the weapon");
       }
    return playerMoney;
    }

    public static void equipWeapon(ArrayList<Weapons> weaponInInventory,ArrayList<Weapons> weaponInEquipped,int handsOccupied,int weaponHands,int choice){

       if(!weaponInInventory.isEmpty()){
           System.out.println("Equip Some Weapon");
           for(int i = 0 ; i < weaponInInventory.size();i++){
               System.out.println(weaponInInventory.get(i).Name);

           }
           System.out.println("Hands Occupied In :" +handsOccupied);

       if(handsOccupied == 0){
        if(weaponHands == 1){

        weaponInEquipped.add(weaponInInventory.get(choice));
        weaponInInventory.remove(weaponInInventory.get(choice));
        handsOccupied++;
            isEquipped = true;

            System.out.println("Ouput I"+handsOccupied);

        }
        if(weaponHands == 2){
            System.out.println("It entered.");
            weaponInEquipped.add(weaponInInventory.get(choice));
            weaponInInventory.remove(weaponInInventory.get(choice));
            handsOccupied+=2;
            isEquipped = true;
            System.out.println(handsOccupied);
        }

    }
    if(handsOccupied == 1){
        if(weaponHands == 1){
            if(!weaponInInventory.isEmpty()){
            weaponInEquipped.add(weaponInInventory.get(choice));
            weaponInInventory.remove(weaponInInventory.get(choice));
            handsOccupied++;
            isEquipped = true;
            System.out.println("Occupied but why: "+handsOccupied);
        }
        }
    }
    else if(handsOccupied == 2){
        System.out.println("You already have two weapons in Hands.");
        isEquipped = false;
    }
       }
       else{
           System.out.println("You don't have anything in Inventory.");
           isEquipped = false;
       }
       }
    public static void equipArmor(ArrayList<Armor> armorInInventory,ArrayList<Armor> armorInEquipped,int choice){
        System.out.println("Armors : In Inventory");
        for(int i = 0 ; i < armorInInventory.size();i++){
            System.out.println(armorInInventory.get(i).Name);

        }

        if(!armorInInventory.isEmpty()){
            if(armorInEquipped.isEmpty()){
                armorInEquipped.add(armorInInventory.get(choice));
                armorInInventory.remove(armorInInventory.get(choice));
            }
            else{
                System.out.println("You already wearing an armor , drop it first .");
            }
        }
        else{
            System.out.println("You don't have anything in Inventory.");
        }
    }

        public static void dropWeaponToInventory(ArrayList<Weapons> weaponInInventory,ArrayList<Weapons> weaponInEquipped,int handsOccupied,int weaponHands,int choice)
        {

            if(!weaponInEquipped.isEmpty()){
            System.out.println(handsOccupied);
            if (handsOccupied == 2) {
                if (weaponHands == 1) {

                    weaponInInventory.add(weaponInEquipped.get(choice));
                    weaponInEquipped.remove(choice);
                    handsOccupied--;
                    isDropped = true;
                    System.out.println("I");

                }
                if (weaponHands == 2) {

                    weaponInInventory.add(weaponInEquipped.get(choice));
                    weaponInEquipped.remove(choice);
                    --handsOccupied ;
                    --handsOccupied ;
                    isDropped = true;
                    System.out.println(handsOccupied);


                    System.out.println("II");
                }

            }
            if (handsOccupied == 1) {
                if (weaponHands == 1) {
                    if (weaponInInventory.isEmpty()) {


                        weaponInInventory.add(weaponInEquipped.get(choice));
                        weaponInEquipped.remove(choice);
                        handsOccupied--;
                        isDropped = true;
                        System.out.println(handsOccupied);

                        System.out.println("III");
                    }
                }
            }}
            else{
                System.out.println("You don't have anything in your hand.");
                isDropped = false;
            }
        }

        public static void checkMoney(int gold , int price ,Heroes player,Weapons weapons){
            if(gold > price){
                gold=WeaponMoneyReduced(gold,price);
                player.weaponInventory.add(weapons);

            }
            else{
                System.out.println("You don't have enough money to buy .");
                System.out.println("(1) Choose Another Weapon ");
                System.out.println("(2) Go without Weapon ");
                int newChoice = UserInput("-->" , 2);
                if(newChoice ==1){
                weapons =(Weapons) weapons.chooseWeapon();
                checkMoney(gold,price , player,weapons);
                }
                else if(newChoice == 2){

                }
            }
        }
    public static void checkArmorMoney(int gold , int price ,Heroes player,Armor armor){
        if(gold > price){
            gold=WeaponMoneyReduced(gold,price);
            player.armorInventory.add(armor);

        }
        else{
            System.out.println("You don't have enough money to buy .");
            System.out.println("(1) Choose Another Armor ");
            System.out.println("(2) Go without Armor ");
            int newChoice = UserInput("-->" , 2);
            if(newChoice ==1){
                armor =(Armor) armor.chooseArmor();
                checkArmorMoney(gold,price , player,armor);
            }
            else if(newChoice == 2){

            }
        }
    }
        public static int chooseWeapon(ArrayList<Weapons> weaponIninventory){
       int choice = UserInput("-->",weaponIninventory.size());
       return choice;
        }
    public static int chooseArmor(ArrayList<Armor> armorIninventory){
        int choice = UserInput("-->",armorIninventory.size());
        return choice;
    }

    public static void dropWeaponfromInventory(ArrayList<Weapons> weaponInInventory,ArrayList<Weapons> weaponInEquipped,int handsOccupied,int weaponHands,int choice) {
       if(!weaponInInventory.isEmpty()) {
       if (handsOccupied == 0) {
            if (weaponHands == 1) {

                weaponInEquipped.add(weaponInInventory.get(choice));
                weaponInInventory.remove(weaponInInventory.get(choice));
                handsOccupied++;

            }
            if (weaponHands == 2) {
                weaponInEquipped.add(weaponInInventory.get(choice));
                weaponInInventory.remove(weaponInInventory.get(choice));
                handsOccupied += 2;
                System.out.println(handsOccupied);
            }

        }
        if (handsOccupied == 1) {
            if (weaponHands == 1) {
                if (!weaponInInventory.isEmpty()) {
                    weaponInEquipped.add(weaponInInventory.get(choice));
                    weaponInInventory.remove(weaponInInventory.get(choice));
                    handsOccupied++;
                    System.out.println(handsOccupied);
                }
            }
        }}
       else{
           System.out.println("You don't have anything in your inventory.");
       }
    }
        public static void StartGame(){
        boolean set = false;
        String Name = null;
        printSeparation(40);
        printSeparation(30);
        System.out.println("GAME : STARTS");
        System.out.println("Enter your Name : Please !");
        printSeparation(30);
        printSeparation(40);
        while(!set) {
            Name = input.next();
            while (Name.equals(null)) {
                System.out.println("Enter a correct name");
                Name = input.next();
            }
            printHeader("Your Name : " + Name + " Is that Correct ? ");
            System.out.println("(1) Yes .");
            System.out.println("(2) No ,Please Change My Name.");
            int newChoice = UserInput("-->", 2);
            if (newChoice == 1) {
                set = true;
            }
        }
            System.out.println("Choose the Difficulty Mode :");
            System.out.println("(1) Easy");
            System.out.println("(2) Normal");
            System.out.println("(3) Hard");
            modeChoice = UserInput("-->",3);

        CharacterStory.printPlotStart();

//            System.out.println("Enter the Mode for the Game : ");
//            System.out.println("(1) Easy ");
//            System.out.println("(2) Moderate ");
//            System.out.println("(3) Hard ");
//            int modeChoice = UserInput("-->",3);
        player = new Heroes(Name,100,100,0,0,0,0,0,0,0,0,0);
        printHeader("Enter the Number of Heroes : ");
        NumberOfHeroes = UserInput("-->",3);
        for(int i = 0 ; i < NumberOfHeroes ; i++){
        player = (Heroes) player.chooseType();
        HeroList.add(player);
        weapons = new Weapons(Name,0,0,0,0);
        weapons.initializeWeaponInventory();
        weapons =(Weapons) weapons.chooseWeapon();
//        if(player.gold > weapons.price){
//        player.gold=WeaponMoneyReduced(player.gold,weapons.price);
//        player.weaponInventory.add(weapons);
//
//        }
//        else{
//            System.out.println("You don't have enough money to buy .");
//        }
        checkMoney(HeroList.get(i).gold,weapons.price,player,weapons);
//        System.out.println("Equip some Weapon : ");
//        System.out.println("Weapon Inventory Size : "+player.weaponInventory.size());
//        Scanner sc = new Scanner(System.in);
//        int n =0;
//       while(n < player.weaponInventory.size()){
//        System.out.println("Weapon Inventory : "+player.weaponInventory.get(n).Name);
//        n++;
//   }
//        System.out.println("Equip Your Weapon from Inventory");
//        int choice = UserInput("Choose your Weapon Wisely" , player.weaponInventory.size());
//
//        equipWeapon(player.weaponInventory,player.equippedWeapon,player.inventory,weapons.noHands,choice-1);
//
//        System.out.println(player.CharacterName+" equipped "+player.equippedWeapon.get(0).Name);
//        System.out.println();
            if(!HeroList.get(i).weaponInventory.isEmpty()){
            int choice =chooseWeapon(HeroList.get(i).weaponInventory);
            equipWeapon(HeroList.get(i).weaponInventory,HeroList.get(i).equippedWeapon,HeroList.get(i).inventory,weapons.noHands,choice-1);

                if(!HeroList.get(i).equippedWeapon.isEmpty()){
                    if(isEquipped){
                        HeroList.get(i).inventory += weapons.noHands;
                }}
                System.out.println("Hands Occupied Output : "+HeroList.get(i).inventory);
            }
            else{

            }
            armor = new Armor(Name,0,0,0);
            armor.initializeArmorInventory();
            armor =(Armor) armor.chooseArmor();

            checkArmorMoney(HeroList.get(i).gold,armor.price,HeroList.get(i),armor);
            if(!HeroList.get(i).armorInventory.isEmpty()){
                int armorchoice = chooseArmor(HeroList.get(i).armorInventory);
                equipArmor(HeroList.get(i).armorInventory,HeroList.get(i).equippedArmor,armorchoice-1);
            }
            else{

            }
            lightningSpells = new LightningSpells(Name,0,0,0,0);
            lightningSpells.initializeLightningSpellInventory();
            lightningSpells =(LightningSpells) lightningSpells.chooseLightningSpell();

            checkLightningSpellsMoney(HeroList.get(i).gold,lightningSpells.price,HeroList.get(i),lightningSpells);
            if(!HeroList.get(i).lightningSpellsInventory.isEmpty()){
                int lightningspellschoice = chooseLightningSpells(HeroList.get(i).lightningSpellsInventory);
                equipLightningSpells(HeroList.get(i).lightningSpellsInventory,HeroList.get(i).equippedlightningSpells,lightningspellschoice-1);
            }
            else{

            }
            fireSpells = new FireSpells(Name,0,0,0,0);
            fireSpells.initializeFireSpellInventory();
            fireSpells =(FireSpells) fireSpells.chooseFireSpell();

            checkFireSpellsMoney(HeroList.get(i).gold,fireSpells.price,HeroList.get(i),fireSpells);
            if(!HeroList.get(i).fireSpellsInventory.isEmpty()){
                int firespellschoice = chooseFireSpells(HeroList.get(i).fireSpellsInventory);
                equipFireSpells(HeroList.get(i).fireSpellsInventory,HeroList.get(i).equippedfireSpells,firespellschoice-1);
            }
            else{

            }
            iceSpells = new IceSpells(Name,0,0,0,0);
            iceSpells.initializeIceSpellInventory();
            iceSpells =(IceSpells) iceSpells.chooseIceSpell();

            checkIceSpellsMoney(HeroList.get(i).gold,iceSpells.price,HeroList.get(i),iceSpells);
            if(!HeroList.get(i).iceSpellsInventory.isEmpty()){
                int icespellschoice = chooseIceSpells(HeroList.get(i).iceSpellsInventory);
                equipIceSpells(HeroList.get(i).iceSpellsInventory,HeroList.get(i).equippediceSpells,icespellschoice-1);
            }
            else{

            }
            potions = new Potions(Name,0,0,0,"");
            potions.initializePotionInventory();
            potions =(Potions) potions.choosePotion();
            checkPotionsMoney(HeroList.get(i).gold,potions.price,HeroList.get(i),potions);
            if(!HeroList.get(i).potionsInventory.isEmpty()){
                int potionschoice = choosePotions(HeroList.get(i).potionsInventory);
                equipPotions(HeroList.get(i).potionsInventory,HeroList.get(i).equippedPotions,potionschoice-1);
            }
            else{

            }
        }
        PrintAllStats();
            System.out.println("Enter Size of the Board : 8 means 8*8");
            size = UserInput("-->",10);
             newBoard.initialize(size,size);
        newBoard.AssignMarket(size*size,'M');

            newBoard.AssignNoManLand(size*size,'N');
        newBoard.AssignLand(size*size,'L');
        newBoard.AddDefaultPos(size,'H');
        System.out.println(" ");
        populateMarket();
        isPacing = true;
        newBoard.userMove(size,'H');
//        CharacterStory.printFirstPlotIntro();

//        gameConditions();
    }

    public static void PrintAllStats(){
        System.out.println("Stats Of All Heroes : ");
        for(int i = 0 ; i < HeroList.size() ; i ++ ){
            System.out.println("("+(i+1)+")"+HeroList.get(i).CharacterName + "  --- > " + HeroList.get(i).equippedWeapon.get(0).Name + " HP : "+HeroList.get(i).HP+" MP :"+ HeroList.get(i).ManaPoints + " Strength Value "+HeroList.get(i).StrengthValue  );
        }
    }
    private static void equipPotions(ArrayList<Potions> potionsInventory, ArrayList<Potions> equippedPotions, int i) {
        System.out.println("IceSpells : In Inventory");
        for(int z = 0 ; z < potionsInventory.size();z++){
            System.out.println(potionsInventory.get(z).Name);

        }

        if(!potionsInventory.isEmpty()){
            if(equippedPotions.isEmpty()){
                equippedPotions.add(potionsInventory.get(i));
                potionsInventory.remove(potionsInventory.get(i));
            }
            else{
                System.out.println("You already have a Potion to consume , drop it first .");
            }
        }
        else{
            System.out.println("You don't have anything in Inventory.");
        }
    }

    private static int choosePotions(ArrayList<Potions> potionsInventory) {
        int choice = UserInput("-->",potionsInventory.size());
        return choice;
    }

    private static void checkPotionsMoney(int gold, int price, Heroes player, Potions potions) {
        if(gold > price){
            gold=WeaponMoneyReduced(gold,price);

            player.potionsInventory.add(potions);

        }
        else{
            System.out.println("You don't have enough money to buy .");
            System.out.println("(1) Choose Another Potion ");
            System.out.println("(2) Go without Potion ");
            int newChoice = UserInput("-->" , 2);
            if(newChoice ==1){
                potions =(Potions) potions.choosePotion();
                checkPotionsMoney(gold,price , player,potions);
            }
            else if(newChoice == 2){

            }
        }

    }

    private static void equipFireSpells(ArrayList<FireSpells> fireSpellsInventory, ArrayList<FireSpells> equippedfireSpells, int i) {
        System.out.println("FireSpells : In Inventory");
        for(int z = 0 ; z < fireSpellsInventory.size();z++){
            System.out.println(fireSpellsInventory.get(z).Name);

        }

        if(!fireSpellsInventory.isEmpty()){
            if(equippedfireSpells.isEmpty()){
                equippedfireSpells.add(fireSpellsInventory.get(i));
                fireSpellsInventory.remove(fireSpellsInventory.get(i));
            }
            else{
                System.out.println("You already have a Fire spell , drop it first .");
            }
        }
        else{
            System.out.println("You don't have anything in Inventory.");
        }
    }


    private static int chooseFireSpells(ArrayList<FireSpells> fireSpellsInventory) {
        int choice = UserInput("-->",fireSpellsInventory.size());
        return choice;
    }

    private static void checkFireSpellsMoney(int gold, int price, Heroes player, FireSpells fireSpells) {
        if(gold > price){
            gold=WeaponMoneyReduced(gold,price);

            player.fireSpellsInventory.add(fireSpells);

        }
        else{
            System.out.println("You don't have enough money to buy .");
            System.out.println("(1) Choose Another Fire Spell ");
            System.out.println("(2) Go without Fire Spell ");
            int newChoice = UserInput("-->" , 2);
            if(newChoice ==1){
                fireSpells =(FireSpells) fireSpells.chooseFireSpell();
                checkFireSpellsMoney(gold,price , player,fireSpells);
            }
            else if(newChoice == 2){

            }
        }
    }

    private static void equipIceSpells(ArrayList<IceSpells> iceSpellsInventory, ArrayList<IceSpells> equippediceSpells, int i) {
        System.out.println("IceSpells : In Inventory");
        for(int z = 0 ; z < iceSpellsInventory.size();z++){
            System.out.println(iceSpellsInventory.get(z).Name);

        }

        if(!iceSpellsInventory.isEmpty()){
            if(equippediceSpells.isEmpty()){
                equippediceSpells.add(iceSpellsInventory.get(i));
                iceSpellsInventory.remove(iceSpellsInventory.get(i));
            }
            else{
                System.out.println("You already have an Ice spell , drop it first .");
            }
        }
        else{
            System.out.println("You don't have anything in Inventory.");
        }
    }

    private static int chooseIceSpells(ArrayList<IceSpells> iceSpellsInventory) {
        int choice = UserInput("-->",iceSpellsInventory.size());
        return choice;
    }

    private static void checkIceSpellsMoney(int gold, int price, Heroes player, IceSpells iceSpells) {
        if(gold > price){
            gold=WeaponMoneyReduced(gold,price);
            player.iceSpellsInventory.add(iceSpells);

        }
        else{
            System.out.println("You don't have enough money to buy .");
            System.out.println("(1) Choose Another Ice Spell ");
            System.out.println("(2) Go without Ice Spell ");
            int newChoice = UserInput("-->" , 2);
            if(newChoice ==1){
                iceSpells =(IceSpells) iceSpells.chooseIceSpell();
                checkIceSpellsMoney(gold,price , player,iceSpells);
            }
            else if(newChoice == 2){

            }
        }
    }

    private static void equipLightningSpells(ArrayList<LightningSpells> lightningSpellsInventory, ArrayList<LightningSpells> equippedlightningSpells, int i) {
        System.out.println("LightningSpells : In Inventory");
        for(int z = 0 ; z < lightningSpellsInventory.size();z++){
            System.out.println(lightningSpellsInventory.get(z).Name);

        }

        if(!lightningSpellsInventory.isEmpty()){
            if(equippedlightningSpells.isEmpty()){
                equippedlightningSpells.add(lightningSpellsInventory.get(i));
                lightningSpellsInventory.remove(lightningSpellsInventory.get(i));
            }
            else{
                System.out.println("You already have a lightning spell , drop it first .");
            }
        }
        else{
            System.out.println("You don't have anything in Inventory.");
        }
    }

    private static int chooseLightningSpells(ArrayList<LightningSpells> lightningSpellsInventory) {
        int choice = UserInput("-->",lightningSpellsInventory.size());
        return choice;
    }

    private static void checkLightningSpellsMoney(int gold, int price, Heroes player, LightningSpells lightningSpells) {
        if(gold > price){
            gold = WeaponMoneyReduced(gold,price);
            System.out.println("spell : "+lightningSpells);
            player.lightningSpellsInventory.add(lightningSpells);

        }
        else{
            System.out.println("You don't have enough money to buy .");
            System.out.println("(1) Choose Another Lightning Spell ");
            System.out.println("(2) Go without Lightning Spell ");
            int newChoice = UserInput("-->" , 2);
            if(newChoice ==1){
                lightningSpells =(LightningSpells) lightningSpells.chooseLightningSpell();
                checkLightningSpellsMoney(gold,price , player,lightningSpells);
            }
            else if(newChoice == 2){

            }
        }
    }

    public static void gameConditions() {
        while(isPacing){

            int Choice = UserInput("--->",3);
            switch(Choice){
                case 1: continueStory(); break;
                case 2 : characterStats(); break;
                case 3 : ; break;
                default : isPacing = false ;break ;
            }

        }
    }

    public static void printallstats(){
        for(int i = 0 ; i < HeroList.size() ;i++){
            if(HeroList.get(i).newType == 1) {
                System.out.println("(" + i + ") " + " Name : " + HeroList.get(i).CharacterName + " Level :" + HeroList.get(i).level + " HP : " + HeroList.get(i).HP + " MP : " + HeroList.get(i).ManaPoints + " XP: " + HeroList.get(i).XP + " Money : " + HeroList.get(i).gold + " Skill Value :"+ HeroList.get(i).StrengthValue);
            }
           else if(HeroList.get(i).newType == 2) {
                System.out.println("(" + i + ") " + " Name : " + HeroList.get(i).CharacterName + " Level :" + HeroList.get(i).level + " HP : " + HeroList.get(i).HP + " MP : " + HeroList.get(i).ManaPoints + " XP: " + HeroList.get(i).XP + " Money : " + HeroList.get(i).gold + " Skill Value :"+HeroList.get(i).dValue);
            }
            else if(HeroList.get(i).newType == 3) {
                System.out.println("(" + i + ") " + " Name : " + HeroList.get(i).CharacterName + " Level :" + HeroList.get(i).level + " HP : " + HeroList.get(i).HP + " MP : " + HeroList.get(i).ManaPoints + " XP: " + HeroList.get(i).XP + " Money : " + HeroList.get(i).gold + " Skill Value :"+HeroList.get(i).agileValue);
            }



        }
    }
    public static void playerDeath(){
        printHeader("May your soul rest in Peace .");
        printHeader("You earned "+ player.XP + " XP on your journey to defeat monsters ");
        System.out.println("Thanks for playing this , do try again for enjoying more of the adventure .");
        System.out.println("Your Game Ended. Sorry Try Again Next Time.");
//        System.out.println("Do you want to Play Again ?");
//        System.out.println("(1) Yes");
//        System.out.println("(2) No");
//        int lastChoice = UserInput("-->",2);
//        if(lastChoice == 1){
//            StartGame();
//        }
//        else{
//            System.out.println("Have a nice day ahead .");
//            System.exit(0);
//        }
//        isPacing = false;
//        System.exit(0);
    }
    private static void continueStory() {
//        newBoard.userMove(8,'H');
        checkStoryPlot();
        if(act != 4){
            RandomEncounter();

        }




    }

    private static void RandomFight() {
    }

    public static int checkAct(ArrayList<Heroes> HeroList){
       int sum = 0 ;
      for(int i =0 ;i < HeroList.size() ; i++){
          sum = HeroList.get(0).level;
          if(sum == HeroList.get(i).level){
              act = sum;
          }
          else{
              System.out.println("Not on Same Level.");
          }
      }


       return act;
    }
    private static void checkStoryPlot() {

        for(int g = 0 ; g < HeroList.size() ; g ++){
            System.out.println("Act " + HeroList.get(g).level);
            if (HeroList.get(g).XP >= Math.pow(levelUp,HeroList.get(g).level) && HeroList.get(g).level == 1) {
                System.out.println("Act " + HeroList.get(g).level);
                HeroList.get(g).level = 2;
                place = 1;
                RandomManacost += RandomManacost*HeroList.get(g).level;
                RandomDamage += RandomDamage*HeroList.get(g).level+RandomDamage;
                RandomPrice += RandomPrice*HeroList.get(g).level;
                RandomLevel += RandomLevel*HeroList.get(g).level;
                RandomDamageReduction += RandomDamageReduction*act +RandomDamageReduction;
                populateMarket();
                CharacterStory.printFirstPlotOutro();
                HeroList.get(g).chooseTrait();
                CharacterStory.printSecondPlotIntro();
                HeroList.get(g).HP = HeroList.get(g).level*100;
                HeroList.get(g).MaxHP = HeroList.get(g).MaxHP*HeroList.get(g).level;

                HeroList.get(g).ManaPoints += 10*HeroList.get(g).ManaPoints;
                if(HeroList.get(g).newType == 1){
                    HeroList.get(g).StrengthValue += 0.1*HeroList.get(g).StrengthValue;
                }
                else{
                    HeroList.get(g).StrengthValue += 0.05*HeroList.get(g).StrengthValue;
                }
                if(HeroList.get(g).newType == 2){
                    HeroList.get(g).dValue += 0.1*HeroList.get(g).dValue;
                }
                else{
                    HeroList.get(g).dValue += 0.05*HeroList.get(g).dValue;
                }
                if(HeroList.get(g).newType == 3){
                    HeroList.get(g).agileValue += 0.1*HeroList.get(g).agileValue;
                }
                else{

                    HeroList.get(g).agileValue += 0.05*HeroList.get(g).agileValue;

                }
                GenerateEnemies(NumberOfHeroes);
                for(int i = 0 ; i < EnemyList.size();i++){
                    System.out.println("This works");

                    EnemyList.get(i).HP += 100*HeroList.get(g).level+100;
                    EnemyList.get(i).MaxHP += 100*HeroList.get(g).level+100;
                    EnemyList.get(i).Damage += 0.05*EnemyList.get(i).Damage;
                    EnemyList.get(i).Defense += 0.05*EnemyList.get(i).Defense;
                    EnemyList.get(i).XP += 0.5*EnemyList.get(i).XP;
                }
                Collision[0] = "Battle";
                Collision[1] = "Battle";
                Collision[2] = "Rest";
                Collision[3] = "Rest";
                Collision[4] = "Battle";
                Collision[5] = "Battle";


            } else if (HeroList.get(g).XP >= Math.pow(levelUp,HeroList.get(g).level) && HeroList.get(g).level == 2) {
                System.out.println("Something works ");

                System.out.println("Act " + HeroList.get(g).level);
                HeroList.get(g).level = 3;
                place = 2;
                RandomManacost += RandomManacost*HeroList.get(g).level;
                RandomDamage += RandomDamage*HeroList.get(g).level+RandomDamage;
                RandomPrice += RandomPrice*HeroList.get(g).level;
                RandomLevel += RandomLevel*HeroList.get(g).level;
                RandomDamageReduction += RandomDamageReduction*HeroList.get(g).level +RandomDamageReduction;
                populateMarket();
                HeroList.get(g).HP = HeroList.get(g).level*100;
                HeroList.get(g).MaxHP = HeroList.get(g).MaxHP*HeroList.get(g).level;

                HeroList.get(g).ManaPoints += 10* HeroList.get(g).ManaPoints;
                CharacterStory.printSecondPlotOutro();
                HeroList.get(g).chooseTrait();

                if( HeroList.get(g).newType == 1){
                    HeroList.get(g).StrengthValue += 0.1* HeroList.get(g).StrengthValue;
                }
                else{
                    HeroList.get(g).StrengthValue += 0.05* HeroList.get(g).StrengthValue;
                }
                if( HeroList.get(g).newType == 2){
                    HeroList.get(g).agileValue += 0.1* HeroList.get(g).agileValue;
                }
                else{
                    HeroList.get(g).agileValue += 0.05* HeroList.get(g).agileValue;
                }
                if( HeroList.get(g).newType == 3){
                    HeroList.get(g).dValue += 0.1* HeroList.get(g).dValue;
                }
                else{
                    HeroList.get(g).dValue += 0.05* HeroList.get(g).dValue;
                }
                CharacterStory.printThirdPlotIntro();
                GenerateEnemies(NumberOfHeroes);
                for(int i = 0 ; i < EnemyList.size();i++){
                    System.out.println("This works");

                    EnemyList.get(i).HP += 100*HeroList.get(g).level+200;
                    EnemyList.get(i).MaxHP += 100*HeroList.get(g).level+200;
                    EnemyList.get(i).Damage += 0.05*EnemyList.get(i).Damage;
                    EnemyList.get(i).Defense += 0.05*EnemyList.get(i).Defense;
                    EnemyList.get(i).XP += 0.5*EnemyList.get(i).XP;
                }
                Collision[0] = "Rest";
                Collision[1] = "Battle";
                Collision[2] = "Battle";
                Collision[3] = "Rest";
                Collision[4] = "Rest";
                Collision[5] = "Rest";


            } else if ( HeroList.get(g).XP >= levelUp*10 &&  HeroList.get(g).level == 3) {
                System.out.println("Act " +  HeroList.get(g).level);
                HeroList.get(g).level = 4;
                place = 3;
                RandomManacost += RandomManacost*HeroList.get(g).level;
                RandomDamage += RandomDamage*HeroList.get(g).level+RandomDamage;
                RandomPrice += RandomPrice*HeroList.get(g).level;
                RandomLevel += RandomLevel*HeroList.get(g).level;
                RandomDamageReduction += RandomDamageReduction*HeroList.get(g).level +RandomDamageReduction;
                populateMarket();
                CharacterStory.printThirdPlotOutro();
                HeroList.get(g).HP = HeroList.get(g).level*100;
                HeroList.get(g).MaxHP = HeroList.get(g).MaxHP*HeroList.get(g).level;
                HeroList.get(g).ManaPoints += 10*HeroList.get(g).ManaPoints;

                if(HeroList.get(g).newType == 1){
                    HeroList.get(g).StrengthValue += 0.1*HeroList.get(g).StrengthValue;
                }
                else{
                    HeroList.get(g).StrengthValue += 0.05*HeroList.get(g).StrengthValue;
                }
                if(HeroList.get(g).newType == 2){
                    HeroList.get(g).agileValue += 0.1*HeroList.get(g).agileValue;
                }
                else{
                    HeroList.get(g).agileValue += 0.05*HeroList.get(g).agileValue;
                }
                if(HeroList.get(g).newType == 3){
                    HeroList.get(g).dValue += 0.1*HeroList.get(g).dValue;
                }
                else{
                    HeroList.get(g).dValue += 0.05*HeroList.get(g).dValue;
                }
                HeroList.get(g).chooseTrait();
                CharacterStory.printFourthPlotIntro();
                GenerateEnemies(NumberOfHeroes);
                for(int i = 0 ; i < EnemyList.size();i++){
                    System.out.println("This works");
                    EnemyList.get(i).HP += 100*HeroList.get(g).level+1000;
                    EnemyList.get(i).MaxHP += 100*HeroList.get(g).level+1000;
                    EnemyList.get(i).Damage += 0.05*EnemyList.get(i).Damage;
                    EnemyList.get(i).Defense += 0.05*EnemyList.get(i).Defense;
                    EnemyList.get(i).XP += 0.5*EnemyList.get(i).XP;
                }
                finalWar();
            } else if (HeroList.get(g).XP >= Math.pow(levelUp,HeroList.get(g).level) && HeroList.get(g).level == 4) {
                System.out.println("Act " + HeroList.get(g).level);
                HeroList.get(g).level=5;
                RandomManacost += RandomManacost*HeroList.get(g).level;
                RandomDamage += RandomDamage*HeroList.get(g).level+RandomDamage;
                RandomPrice += RandomPrice*HeroList.get(g).level;
                RandomLevel += RandomLevel*HeroList.get(g).level;
                RandomDamageReduction += RandomDamageReduction*HeroList.get(g).level +RandomDamageReduction;
                populateMarket();
                CharacterStory.printRandomOutro(HeroList.get(g).level);
                HeroList.get(g).HP = HeroList.get(g).level*100;
                HeroList.get(g).MaxHP =   HeroList.get(g).MaxHP*HeroList.get(g).level;
                HeroList.get(g).ManaPoints += 10*  HeroList.get(g).ManaPoints;

                if(  HeroList.get(g).newType == 1){
                    HeroList.get(g).StrengthValue += 0.1*  HeroList.get(g).StrengthValue;
                }
                else{
                    HeroList.get(g).StrengthValue += 0.05*  HeroList.get(g).StrengthValue;
                }
                if(  HeroList.get(g).newType == 2){
                    HeroList.get(g).agileValue += 0.1*  HeroList.get(g).agileValue;
                }
                else{
                    HeroList.get(g).agileValue += 0.05*  HeroList.get(g).agileValue;
                }
                if(  HeroList.get(g).newType == 3){
                    HeroList.get(g).dValue += 0.1*  HeroList.get(g).dValue;
                }
                else{
                    HeroList.get(g).dValue += 0.05*  HeroList.get(g).dValue;
                }
//            player.chooseTrait();
                CharacterStory.printRandomIntro(  HeroList.get(g).level);
                GenerateEnemies(NumberOfHeroes);
                for(int i = 0 ; i < EnemyList.size();i++){
                    System.out.println("This works");

                    EnemyList.get(i).HP += 100*HeroList.get(g).level+2000;
                    EnemyList.get(i).MaxHP += 100*HeroList.get(g).level+2000;
                    EnemyList.get(i).Damage += 0.05*EnemyList.get(i).Damage;
                    EnemyList.get(i).Defense += 0.05*EnemyList.get(i).Defense;
                    EnemyList.get(i).XP += 0.5*EnemyList.get(i).XP;
                }
                randomWar(EnemyList);
            }
            else if (  HeroList.get(g).XP >= Math.pow(levelUp,  HeroList.get(g).level) &&   HeroList.get(g).level == seriousAct) {
                System.out.println("Act " +   HeroList.get(g).level);

                CharacterStory.printRandomOutro(  HeroList.get(g).level);
                seriousAct =   HeroList.get(g).level++;
                RandomManacost += RandomManacost*  HeroList.get(g).level;
                RandomDamage += RandomDamage*  HeroList.get(g).level+RandomDamage;
                RandomPrice += RandomPrice*  HeroList.get(g).level;
                RandomLevel += RandomLevel*  HeroList.get(g).level;
                RandomDamageReduction += RandomDamageReduction*  HeroList.get(g).level +RandomDamageReduction;
                populateMarket();
                HeroList.get(g).HP =   HeroList.get(g).level*100;
                HeroList.get(g).MaxHP =   HeroList.get(g).MaxHP*  HeroList.get(g).level;
                HeroList.get(g).ManaPoints += 10*  HeroList.get(g).ManaPoints;

                if(  HeroList.get(g).newType == 1){
                    HeroList.get(g).StrengthValue += 0.1*  HeroList.get(g).StrengthValue;
                }
                else{
                    HeroList.get(g).StrengthValue += 0.05*  HeroList.get(g).StrengthValue;
                }
                if(  HeroList.get(g).newType == 2){
                    HeroList.get(g).agileValue += 0.1*  HeroList.get(g).agileValue;
                }
                else{
                    HeroList.get(g).agileValue += 0.05*  HeroList.get(g).agileValue;
                }
                if(  HeroList.get(g).newType == 3){
                    HeroList.get(g).dValue += 0.1*  HeroList.get(g).dValue;
                }
                else{
                    HeroList.get(g).dValue += 0.05*  HeroList.get(g).dValue;
                }
//            player.chooseTrait();
                CharacterStory.printRandomIntro(  HeroList.get(g).level);
                GenerateEnemies(NumberOfHeroes);
                for(int i = 0 ; i < EnemyList.size();i++){
                    System.out.println("This works");

                    EnemyList.get(i).HP += 100*HeroList.get(g).level+3000;
                    EnemyList.get(i).MaxHP += 100*HeroList.get(g).level+300;
                    EnemyList.get(i).Damage += 0.05*EnemyList.get(i).Damage;
                    EnemyList.get(i).Defense += 0.05*EnemyList.get(i).Defense;

                    EnemyList.get(i).XP += 0.5*EnemyList.get(i).XP;
                }
                randomWar(EnemyList);
            }
        }}

    public static void RandomCollision(){
        System.out.println("You have encountered an Enemy , In order to survive you have to fight it.");

        System.out.println("Number of Heroes "+NumberOfHeroes );
        GenerateEnemies(NumberOfHeroes);
        for(int i = 0 ; i < EnemyList.size();i++){
            System.out.println("This works");

            EnemyList.get(i).HP += 100*act;
            EnemyList.get(i).MaxHP += 100*act;
            EnemyList.get(i).Damage += 0.05*EnemyList.get(i).Damage;
            EnemyList.get(i).Defense += 0.05*EnemyList.get(i).Defense;

            EnemyList.get(i).XP += 0.5*EnemyList.get(i).XP;
        }
//        ArrayList<Villains> EncounterEnemies = new ArrayList<Villains>();
//        for(int i = 0 ; i < 3 ; i ++){
//            EncounterEnemies.add(EnemyList.get(i));
//        }

        Collision(EnemyList);
//        for(int i = 0 ; i < FaintedHeroList.size(); i++){
//            HeroList.add(FaintedHeroList.get(i));
//            FaintedHeroList.get(i).HP = FaintedHeroList.get(i).MaxHP/2;
//            FaintedHeroList.remove(i);
//
//        }
//        for(int j=0;j<HeroList.size();j++){
//            System.out.println(HeroList.get(j).CharacterName);
//        }
        newBoard.userMove(size,'H');
    }
    public static ArrayList<Villains> randomVillain(ArrayList<Villains> villainList){
        Random a = new Random();
       ArrayList<Villains> RandomV = new ArrayList<Villains>();
        for(int i = 0 ; i< HeroList.size() ; i++){
            int newVilain = a.nextInt(villainList.size())+0;
            RandomV.add(villainList.get(newVilain));
        }
        return  RandomV;
    }
    public static void randomWar(ArrayList<Villains> EnemyList){

        System.out.println("You have encountered an Enemy , In order to survive you have to fight it.");

        System.out.println("Number of Heroes "+NumberOfHeroes );
//        GenerateEnemies(NumberOfHeroes);

        Collision(randomVillain(EnemyList));
//        for(int i = 0 ; i < FaintedHeroList.size(); i++){
//            HeroList.add(FaintedHeroList.get(i));
//            FaintedHeroList.remove(i);
//
//        }
//        for(int j=0;j<HeroList.size();j++){
//            System.out.println(HeroList.get(j).CharacterName);
//        }
        newBoard.userMove(size,'H');
    }
    public static void checkPlayerHealth(Heroes hero){

    }
    public static void inventorydisplay(){
       for(int s = 0 ; s< HeroList.size();s++){
       System.out.println("Inventory Choices :");
        System.out.println("(1) Consume Potion");
        System.out.println("(2) See Inventory(Weapons, Armors, Spells)");
        System.out.println("(3) Drop To Inventory(Weapon)");
        System.out.println("(4) Equip from Inventory(Weapon)");
        System.out.println("(5) Equip from Inventory(Armor)");
        System.out.println("(6) Drop To Inventory(Armor)");
        System.out.println("(7) Equip from Inventory(Spell)");
        System.out.println("(8) Drop To Inventory(Spell)");
        System.out.println("(9) Equip from Inventory(Potion)");
        System.out.println("(10) Drop To Inventory(Potion)");
        int sc = UserInput("-->",10);

        if(sc == 1 ){
//                    if(player.pots >0 && player.HP < player.MaxHP){
//                        printHeader("Do you want to consume a potion? ("+player.pots+" left)." );
//                        System.out.println("(1) Yes");
//                        System.out.println("(2) No ");
//                        int choice =  UserInput(" -- >",2);
//                        if(choice == 1){
//                            player.HP = player.MaxHP;
//                            printHeader("You drank a magic potion and restored your health.");
//
//                        }
//                    }
//                    else{
//                        printHeader("You don't have any potions or maybe you have full health restored already . ");
//
//                    }
            if(!HeroList.get(s).equippedPotions.isEmpty()){
                printHeader("Do you want to consume the potion equipped by you ? ");
                System.out.println("(1) Yes");
                System.out.println("(2) No");
                int choice = UserInput("-->" , 2);
                if(choice == 1){
                    if(HeroList.get(s).equippedPotions.get(0).Attribute == "Health"){
                        HeroList.get(s).HP += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                        System.out.println("Your Health Increased After consuming the potion.");
                    }
                    else if(HeroList.get(s).equippedPotions.get(0).Attribute == "Strength"){
                        HeroList.get(s).StrengthValue += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                        System.out.println("Your Strength Increased After consuming the potion.");

                    }
                    else if(HeroList.get(s).equippedPotions.get(0).Attribute == "Mana"){
                        HeroList.get(s).ManaPoints += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                        System.out.println("Your MP Increased After consuming the potion.");
                    }
                    else if(HeroList.get(s).equippedPotions.get(0).Attribute == "Agility"){
                        HeroList.get(s).agileValue += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                        System.out.println("Your Agility Increased After consuming the potion.");

                    }
                    else if(HeroList.get(s).equippedPotions.get(0).Attribute == "Any"){
                        Random b = new Random();
                        int randomChoice = b.nextInt(4)+1;
                        if(randomChoice == 1){
                            HeroList.get(s).HP += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                            System.out.println("Your Health Increased After consuming the potion.");
                        }
                        if(randomChoice == 2){

                            HeroList.get(s).StrengthValue += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                            System.out.println("Your Strength Increased After consuming the potion.");
                        }
                        if(randomChoice == 3){
                            HeroList.get(s).ManaPoints += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                            System.out.println("Your MP Increased After consuming the potion.");
                        }
                        if(randomChoice == 4){
                            HeroList.get(s).agileValue += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                            System.out.println("Your Agility Increased After consuming the potion.");

                        }

                    }
                    else if(HeroList.get(s).equippedPotions.get(0).Attribute == "All"){

                        HeroList.get(s).HP += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                        HeroList.get(s).StrengthValue += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                        HeroList.get(s).ManaPoints += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                        HeroList.get(s).agileValue += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                        System.out.println("All your abilities Increased After consuming the potion.");
                    }
                }
            }

        }

        else if(sc == 2){
            System.out.println("Weapons : ");
            for(int i = 0 ; i < HeroList.get(s).weaponInventory.size();i++){
                System.out.println(HeroList.get(s).weaponInventory.get(i).Name+" Damage Value"+HeroList.get(s).weaponInventory.get(i).damageValue+ " Number of Hands Required"+HeroList.get(s).weaponInventory.get(i).noHands+" Level"+HeroList.get(s).weaponInventory.get(i).level);

            }
            System.out.println("Armors : ");
            for(int i = 0 ; i < HeroList.get(s).armorInventory.size();i++){
                System.out.println(HeroList.get(s).armorInventory.get(i).Name+" Level "+HeroList.get(s).armorInventory.get(i).level+" Damage Reduction : "+HeroList.get(s).armorInventory.get(i).damageReductionValue);

            }
            System.out.println("Spells : ");
            System.out.println("Lightning Spells : ");
            for(int i = 0 ; i < HeroList.get(s).lightningSpellsInventory.size();i++){
                System.out.println(HeroList.get(s).lightningSpellsInventory.get(i).Name+" Level : "+HeroList.get(s).lightningSpellsInventory.get(i).level+" Damage : "+HeroList.get(s).lightningSpellsInventory.get(i).Damage+" MP Cost :"+HeroList.get(s).lightningSpellsInventory.get(i).MPCost);

            }
            System.out.println("Fire Spells : ");
            for(int i = 0 ; i < HeroList.get(s).fireSpellsInventory.size();i++){
                System.out.println(HeroList.get(s).fireSpellsInventory.get(i).Name+" Level : "+HeroList.get(s).fireSpellsInventory.get(i).level+" Damage : "+HeroList.get(s).fireSpellsInventory.get(i).Damage+" MP Cost :"+HeroList.get(s).fireSpellsInventory.get(i).MPCost);

            }System.out.println("Ice Spells : ");

            for(int i = 0 ; i < HeroList.get(s).iceSpellsInventory.size();i++){
                System.out.println(HeroList.get(s).iceSpellsInventory.get(i).Name+" Level : "+HeroList.get(s).iceSpellsInventory.get(i).level+" Damage : "+HeroList.get(s).iceSpellsInventory.get(i).Damage+" MP Cost :"+HeroList.get(s).iceSpellsInventory.get(i).MPCost);

            }


        }
        else if (sc==4){
            if(!HeroList.get(s).weaponInventory.isEmpty()){

                for(int k = 0 ; k< HeroList.get(s).weaponInventory.size();k++){
                    System.out.println(HeroList.get(s).weaponInventory.get(k).Name+" Damage Value"+HeroList.get(s).weaponInventory.get(k).damageValue+ " Number of Hands Required"+HeroList.get(s).weaponInventory.get(k).noHands+" Level"+HeroList.get(s).weaponInventory.get(k).level);

                }
                System.out.println("Equip Your Weapon from Inventory");
                int choice = UserInput("Choose your Weapon Wisely" , HeroList.get(s).weaponInventory.size());
                System.out.println(HeroList.get(s).inventory);

                equipWeapon(HeroList.get(s).weaponInventory,HeroList.get(s).equippedWeapon,HeroList.get(s).inventory,weapons.noHands,choice-1);
                if(!HeroList.get(s).equippedWeapon.isEmpty()){
                    if(isEquipped){
                        HeroList.get(s).inventory += weapons.noHands;
                    }
                }
                System.out.println(HeroList.get(s).inventory);
                System.out.println("It finished");
                System.out.println(HeroList.get(s).CharacterName+" equipped "+HeroList.get(s).equippedWeapon.get(0).Name);}
            else{
                System.out.println("You don't have any weapons in your inventory.");
            }
        }
        else if (sc==5){
            if(!HeroList.get(s).armorInventory.isEmpty()){
                int k = 0;
                while(k < HeroList.get(s).armorInventory.size()){
                    System.out.println(HeroList.get(s).armorInventory.get(k).Name+" Level "+HeroList.get(s).armorInventory.get(k).level+" Damage Reduction : "+HeroList.get(s).armorInventory.get(k).damageReductionValue);
                    k++;
                }
                System.out.println("Equip Your Armor from Inventory");
                int choice = UserInput("Choose your Armor Wisely" , HeroList.get(s).armorInventory.size());

                equipArmor(HeroList.get(s).armorInventory,HeroList.get(s).equippedArmor,choice-1);

                System.out.println("It finished");
                System.out.println(HeroList.get(s).CharacterName+" equipped "+HeroList.get(s).equippedArmor.get(0).Name);}
            else{
                System.out.println("You don't have any Armor in your inventory.");
            }
        }
        else if (sc==7){
            System.out.println("Choose Type of Spell");
            System.out.println("(1) Lightning Spell");
            System.out.println("(2) Fire Spell");
            System.out.println("(3) Ice Spell");
            int newChoice = UserInput("->",3);
            if(newChoice == 1){
                if(!HeroList.get(s).lightningSpellsInventory.isEmpty()){
                    int k = 0;
                    while(k < HeroList.get(s).lightningSpellsInventory.size()){
                        System.out.println(HeroList.get(s).lightningSpellsInventory.get(k).Name+" Level : "+HeroList.get(s).lightningSpellsInventory.get(k).level+" Damage : "+HeroList.get(s).lightningSpellsInventory.get(k).Damage+" MP Cost :"+HeroList.get(s).lightningSpellsInventory.get(k).MPCost);
                        k++;
                    }
                    System.out.println("Equip Your Lightning Spell from Inventory");
                    int choice = UserInput("Choose your Spell Wisely" , HeroList.get(s).lightningSpellsInventory.size());

                    equipLightningSpells(HeroList.get(s).lightningSpellsInventory,HeroList.get(s).equippedlightningSpells,choice-1);

                    System.out.println("It finished");
                    System.out.println(HeroList.get(s).CharacterName+" equipped "+HeroList.get(s).equippedlightningSpells.get(0).Name);}
                else{
                    System.out.println("You don't have any Lightning Spells in your inventory.");
                }
            }
            else if(newChoice == 2){
                if(!HeroList.get(s).fireSpellsInventory.isEmpty()){
                    int k = 0;
                    while(k < HeroList.get(s).fireSpellsInventory.size()){
                        System.out.println(HeroList.get(s).fireSpellsInventory.get(k).Name+" Level : "+HeroList.get(s).fireSpellsInventory.get(k).level+" Damage : "+HeroList.get(s).fireSpellsInventory.get(k).Damage+" MP Cost :"+HeroList.get(k).fireSpellsInventory.get(k).MPCost);
                        k++;
                    }
                    System.out.println("Equip Your Fire Spell from Inventory");
                    int choice = UserInput("Choose your Spell Wisely" , HeroList.get(s).fireSpellsInventory.size());

                    equipFireSpells(HeroList.get(s).fireSpellsInventory,HeroList.get(s).equippedfireSpells,choice-1);

                    System.out.println("It finished");
                    System.out.println(HeroList.get(s).CharacterName+" equipped "+HeroList.get(s).equippedfireSpells.get(0).Name);}
                else{
                    System.out.println("You don't have any Fire Spells in your inventory.");
                }


            }
            else if(newChoice == 3){
                if(!HeroList.get(s).iceSpellsInventory.isEmpty()){
                    int k = 0;
                    while(k < HeroList.get(s).iceSpellsInventory.size()){
                        System.out.println(HeroList.get(s).iceSpellsInventory.get(k).Name+" Level : "+HeroList.get(s).iceSpellsInventory.get(k).level+" Damage : "+HeroList.get(s).iceSpellsInventory.get(k).Damage+" MP Cost :"+HeroList.get(s).iceSpellsInventory.get(k).MPCost);
                        k++;
                    }
                    System.out.println("Equip Your Ice Spells from Inventory");
                    int choice = UserInput("Choose your Ice Spells Wisely" , HeroList.get(s).iceSpellsInventory.size());

                    equipIceSpells(HeroList.get(s).iceSpellsInventory,HeroList.get(s).equippediceSpells,choice-1);

                    System.out.println("It finished");
                    System.out.println(HeroList.get(s).CharacterName+" equipped "+HeroList.get(s).equippediceSpells.get(0).Name);}
                else{
                    System.out.println("You don't have any Ice Spells in your inventory.");
                }
            }
        }
        else if (sc==6){
            if(!HeroList.get(s).equippedArmor.isEmpty()) {
                int k = 0;int l = 0;
                while (k < HeroList.get(s).armorInventory.size()) {
                    System.out.println("Armor Inventory : " + HeroList.get(s).armorInventory.get(k).Name);
                    k++;
                }
                while (l < HeroList.get(s).equippedArmor.size()) {
                    System.out.println("Armor Equipped : " + HeroList.get(s).equippedArmor.get(l).Name);
                    l++;
                }
                int choice = UserInput("Choose the Armor you want to drop", HeroList.get(s).equippedArmor.size());
                dropArmorToInventory(HeroList.get(s).armorInventory, HeroList.get(s).equippedArmor, choice - 1);


                System.out.println(HeroList.get(s).CharacterName + " dropped " + HeroList.get(s).armorInventory.get(0).Name);
                for(int i = 0 ; i < HeroList.get(s).armorInventory.size();i++){
                    System.out.println("Player Armor Inventory : "+HeroList.get(s).armorInventory.get(i).Name);
                }
            }
            else{
                System.out.println("You aren't wearing any armor .");
            }

        }else if (sc==8){
            System.out.println("Choose Type of Spell");
            System.out.println("(1) Lightning Spell");
            System.out.println("(2) Fire Spell");
            System.out.println("(3) Ice Spell");
            int newChoice = UserInput("->",3);
            if(newChoice == 1){
                if(!HeroList.get(s).equippedlightningSpells.isEmpty()) {
                    int k = 0;int l = 0;
                    while (k < HeroList.get(s).lightningSpellsInventory.size()) {
                        System.out.println("Lightning Spell Inventory : " + HeroList.get(s).lightningSpellsInventory.get(k).Name);
                        k++;
                    }
                    while (l < HeroList.get(s).equippedlightningSpells.size()) {
                        System.out.println("Lightning Spells Equipped : " + HeroList.get(s).equippedlightningSpells.get(l).Name);
                        l++;
                    }
                    int choice = UserInput("Choose the Lightning Spells you want to drop", HeroList.get(s).equippedlightningSpells.size());
                    dropLightningSpellToInventory(HeroList.get(s).lightningSpellsInventory, HeroList.get(s).equippedlightningSpells, choice - 1);


                    System.out.println(HeroList.get(s).CharacterName + " dropped " + HeroList.get(s).lightningSpellsInventory.get(0).Name);
                }
                else{
                    System.out.println("You don't have any Lightning Spells .");
                }

            }else if(newChoice == 2){
                if(!HeroList.get(s).equippedfireSpells.isEmpty()) {
                    int k = 0;int l = 0;
                    while (k < HeroList.get(s).fireSpellsInventory.size()) {
                        System.out.println("Fire Spells Inventory : " + HeroList.get(s).fireSpellsInventory.get(k).Name);
                        k++;
                    }
                    while (l < HeroList.get(s).equippedArmor.size()) {
                        System.out.println("Fire Spells Equipped : " + HeroList.get(s).equippedfireSpells.get(l).Name);
                        l++;
                    }
                    int choice = UserInput("Choose the Fire Spell you want to drop", HeroList.get(s).equippedfireSpells.size());
                    dropFireSpellToInventory(HeroList.get(s).fireSpellsInventory, HeroList.get(s).equippedfireSpells, choice - 1);


                    System.out.println(HeroList.get(s).CharacterName + " dropped " + HeroList.get(s).fireSpellsInventory.get(0).Name);
                }
                else{
                    System.out.println("You don't have any Fire Spells ");
                }


            }else if(newChoice == 3)
            {
                if(!HeroList.get(s).equippediceSpells.isEmpty()) {
                    int k = 0;int l = 0;
                    while (k < HeroList.get(s).iceSpellsInventory.size()) {
                        System.out.println("Ice Spell Inventory : " + HeroList.get(s).iceSpellsInventory.get(k).Name);
                        k++;
                    }
                    while (l < HeroList.get(s).equippediceSpells.size()) {
                        System.out.println("Ice Spells Equipped : " + HeroList.get(s).equippediceSpells.get(l).Name);
                        l++;
                    }
                    int choice = UserInput("Choose the Ice Spell you want to drop", HeroList.get(s).equippediceSpells.size());
                    dropIceSpellToInventory(HeroList.get(s).iceSpellsInventory, HeroList.get(s).equippediceSpells, choice - 1);


                    System.out.println(HeroList.get(s).CharacterName + " dropped " + HeroList.get(s).iceSpellsInventory.get(0).Name);
                }
                else{
                    System.out.println("You don't have any Ice spells.");
                }

            }

        }
        else if (sc==3){
            if(!HeroList.get(s).equippedWeapon.isEmpty()) {
                int k = 0;
                int l= 0;
                while (k < HeroList.get(s).weaponInventory.size()) {
                    System.out.println("Weapon Inventory : " + HeroList.get(s).weaponInventory.get(k).Name);
                    k++;
                }
                while (l < HeroList.get(s).equippedWeapon.size()) {
                    System.out.println("Weapon Equipped : " + HeroList.get(s).equippedWeapon.get(l).Name);
                    l++;
                }
                int choice = UserInput("Choose the Weapon you want to drop", HeroList.get(s).equippedWeapon.size());
//                        for (int i = 0; i < player.equippedWeapon.size(); i++) {
//                            player.inventory += player.equippedWeapon.get(i).noHands;
//                        }
                dropWeaponToInventory(HeroList.get(s).weaponInventory, HeroList.get(s).equippedWeapon, HeroList.get(s).inventory, weapons.noHands, choice - 1);
                if(isDropped){

                    if (HeroList.get(s).inventory == 1) {
                        if (weapons.noHands == 1) {
                            HeroList.get(s).inventory -= 1;
                            System.out.println("III");
                        }
                    }
                    if (HeroList.get(s).inventory == 2) {
                        if (weapons.noHands == 1) {
                            HeroList.get(s).inventory -= 1;
                            System.out.println("I");
                        } else if (weapons.noHands == 2) {
                            HeroList.get(s).inventory -= 2;
                            System.out.println("II");
                        }
                    }



                }
                System.out.println("Hands Occupied " + HeroList.get(s).inventory);

                System.out.println(HeroList.get(s).CharacterName + " dropped " + HeroList.get(s).weaponInventory.get(0).Name);
            }
            else{
                System.out.println("You don't have any weapon in your hand.");
            }

        } else if (sc == 9) {
            if(!HeroList.get(s).potionsInventory.isEmpty()){
                int k = 0;
                while(k < HeroList.get(s).potionsInventory.size()){
                    System.out.println("Potion Inventory : "+HeroList.get(s).potionsInventory.get(k).Name);
                    k++;
                }
                System.out.println("Equip Your Potion from Inventory");
                int choice = UserInput("Choose your Potion Wisely" , HeroList.get(s).armorInventory.size());

                equipPotions(HeroList.get(s).potionsInventory,HeroList.get(s).equippedPotions,choice-1);

                System.out.println("It finished");
                System.out.println(HeroList.get(s).CharacterName+" equipped "+HeroList.get(s).equippedPotions.get(0).Name);}
            else{
                System.out.println("You don't have any Potions in your inventory.");
            }
        }
        else if(sc ==10){
            if(!HeroList.get(s).equippedPotions.isEmpty()) {
                int k = 0;int l = 0;
                while (k < HeroList.get(s).potionsInventory.size()) {
                    System.out.println("Potion Inventory : " + HeroList.get(s).potionsInventory.get(k).Name);
                    k++;
                }
                while (l < HeroList.get(s).equippedArmor.size()) {
                    System.out.println("Potion Equipped : " + HeroList.get(s).potionsInventory.get(l).Name);
                    l++;
                }
                int choice = UserInput("Choose the Potion you want to drop", HeroList.get(s).equippedPotions.size());
                dropPotionToInventory(HeroList.get(s).potionsInventory, HeroList.get(s).equippedPotions, choice - 1);


                System.out.println(HeroList.get(s).CharacterName + " dropped " + HeroList.get(s).potionsInventory.get(0).Name);
                for(int i = 0 ; i < HeroList.get(s).potionsInventory.size();i++){
                    System.out.println("Player Potion Inventory : "+HeroList.get(s).potionsInventory.get(i).Name);
                }
            }
            else{
                System.out.println("You don't have any potions .");
            }
        }


       }
   }

    public static void Collision(ArrayList<Villains> villain){
       for(int y = 0 ; y <  HeroList.size();y++){
           System.out.println(HeroList.get(y));
       }
        while(true) {

            for(int s =0,e=0 ;s<HeroList.size()&& e<HeroList.size();s++ ,e++){

//                 if(HeroList.get(s).HP<0){
//                    if(HeroList.get(s+1).HP<0){
//                        if(HeroList.get(s+2).HP<0){
//                            System.out.println("Game Over");
//                            System.exit(0);
//                    }
//                }
//                 }
//                    else if(HeroList.get(s).HP<0){
//                        if(HeroList.get(s+1).HP<0){
//                            System.out.println("Player "+HeroList.get(s).CharacterName+"&&"+HeroList.get(s+1).CharacterName+" fainted .");
//                            s+=2;
//                            break;
//                        }
//                    }
                if(HeroList.isEmpty()){
                    System.out.println("Your Game Ended. Sorry Try Again Next Time.");
                    System.out.println("Do you want to Play Again ?");
                    System.out.println("(1) Yes");
                    System.out.println("(2) No");
                    int lastChoice = UserInput("-->",2);
                    if(lastChoice == 1){
                        StartGame();
                    }
                    else{
                        System.out.println("Have a nice day ahead .");
                    System.exit(0);
                    }
                }
                if(HeroList.get(s).HP<0){
                    System.out.println("Player "+HeroList.get(s).CharacterName+"has fainted . Now it's up on the remaining Heroes to defend your Honor.");

                    FaintedHeroList.add(HeroList.get(s));

                    HeroList.remove(HeroList.get(s));
//                    if(HeroList.isEmpty()){
//                        System.out.println("Your Game Ended. Sorry Try Again Next Time.");
//                        System.out.println("Do you want to Play Again ?");
//                        System.out.println("(1) Yes");
//                        System.out.println("(2) No");
//                        int lastChoice = UserInput("-->",2);
//                        if(lastChoice == 1){
//                            StartGame();
//                        }
//                        else{
//                            System.out.println("Have a nice day ahead .");
//                            System.exit(0);
//                        }
//                    }

                    break;

                }
                if(villain.isEmpty()){
                    newBoard.userMove(size,'H');
                }
                if(e > villain.size()){
                    e--;
                }
                if(e<villain.size()){
                if(villain.get(e).HP < 0){
                    System.out.println("Enemy "+villain.get(e).CharacterName+" has lost from "+HeroList.get(e).CharacterName);

                    DeadVillainList.add(villain.get(e));
                    villain.remove(villain.get(e));
                    break;
                }
//                if(villain.get(s).HP < 0){
//                    if(villain.get(s+1).HP < 0){
//                        if(villain.get(s+2).HP < 0){
//
//                    System.out.println("Enemy "+villain.get(s).CharacterName+"&&"+villain.get(s+1).CharacterName+"&&"+villain.get(s+2).CharacterName+" has lost ");
//                            newBoard.userMove(size,'H');
//
//                }
//                    }
//                }
//
//                if(villain.get(s).HP < 0){
//                    if(villain.get(s+1).HP < 0){
//
//                    System.out.println("Enemy "+villain.get(s).CharacterName+" has lost from "+HeroList.get(s).CharacterName);
//                    s+=2;
//                    break;
//                }
//                }

//            System.out.println(villain.CharacterName);
            printHeader(villain.get(e).CharacterName + "\n HP: " + villain.get(e).HP + " / " + villain.get(e).MaxHP);
            printHeader(HeroList.get(e).CharacterName + "\n HP: " + HeroList.get(e).HP + " / " + HeroList.get(e).MaxHP);
            System.out.println("Choose am Action to perform :");
            printSeparation(20);
            System.out.println("(1) Attack (with Weapon)");
            System.out.println("(2) Attack (with Spell)");
            System.out.println("(3) Attack (with Hand)");
            System.out.println("(4) Consume Potion");
            System.out.println("(5) See Inventory(Weapons, Armors, Spells)");
            System.out.println("(6) Drop To Inventory(Weapon)");
            System.out.println("(7) Equip from Inventory(Weapon)");
            System.out.println("(8) Run From Battle");
            System.out.println("(9) Equip from Inventory(Armor)");
            System.out.println("(10) Drop To Inventory(Armor)");
            System.out.println("(11) Equip from Inventory(Spell)");
            System.out.println("(12) Drop To Inventory(Spell)");
            System.out.println("(13) Equip from Inventory(Potion)");
            System.out.println("(14) Drop To Inventory(Potion)");
            System.out.println("(15) Check Info OF Heroes");
            System.out.println("(16) Check Info OF Villains");
            int sc = UserInput("Enter your choice",16);

            if (sc == 1){
                    int Damage = HeroList.get(s).Attack() - villain.get(e).Defend();
                    int DamageTaken = villain.get(e).Attack() - HeroList.get(s).Defend();
                    boolean damageTakeNeg = DamageTaken < 0;
                    if(damageTakeNeg){
                        Damage -= DamageTaken/2;
                        DamageTaken = 0;
                    }
                    else if(Damage<0){
                        Damage = 0 ;

                    }
                HeroList.get(s).HP -= DamageTaken;
                villain.get(e).HP -= Damage;
                    printHeader("WAR");
                System.out.println("Damage :"+Damage);

                    System.out.println("You inflicted "+Damage+" damage on the "+villain.get(e).CharacterName + " .");
                    printSeparation(10);
                    System.out.println("The enemy "+ villain.get(e).CharacterName + " inflicted  "+DamageTaken +" on you .");
                    if(HeroList.get(s).HP <=0)
                    {
                        System.out.println("Player HP :"+HeroList.get(s).HP);
                        playerDeath();
                        break;
                    }
                    else if(villain.get(e).HP<=0){
                        printHeader("You defeated the "+ EnemyList.get(s).CharacterName+" !");
                        HeroList.get(s).XP += villain.get(e).XP;
                        HeroList.get(s).HP += 0.2 *HeroList.get(s).MaxHP ;
                        HeroList.get(s).ManaPoints += 0.5*HeroList.get(s).ManaPoints;
                        System.out.println("You earned the " +villain.get(e).XP+" XP!");
                        boolean additionalRest = (Math.random()*5+1 <= 2.25);
                        int MoneyEarned = (int)(Math.random()*villain.get(e).XP*100);
                        int difference = HeroList.size() - FaintedHeroList.size() ;
                        if(difference >0)
                        {
                            MoneyEarned = MoneyEarned*difference;
                        }
                        if(additionalRest){
                            HeroList.get(s).restsLeft ++ ;
                            System.out.println("You earned additional rest gifts .");

                        }
                        if(MoneyEarned> 0){
                            HeroList.get(s).gold += MoneyEarned;
                            System.out.println("You collect "+ MoneyEarned+" gold from the" + villain.get(e).CharacterName+ "'s corpse !");
                        }
                        break;
                    }

                   }
            if(sc == 2){
                System.out.println("Which spell do you want to use : ");
                System.out.println("(1) Lightning Spell ");
                System.out.println("(2) Fire Spell");
                System.out.println("(3) Ice Spell");
                int choice = UserInput("-->",3);
                if(choice == 1){
//                    System.out.println("Mana points"+player.ManaPoints);
//
//                    System.out.println("Lightning Mp Cost "+ player.equippedlightningSpells.get(0).MPCost);
                    if(!HeroList.get(s).equippedlightningSpells.isEmpty()){
                    if(HeroList.get(s).ManaPoints > HeroList.get(s).equippedlightningSpells.get(0).MPCost) {
                        HeroList.get(s).useLightningSpell.add(HeroList.get(s).equippedlightningSpells.get(0));
                        HeroList.get(s).equippedlightningSpells.remove(0);
                        int DodgeChance = HeroList.get(s).LightningSpellAttack() ;
                        int Damage = HeroList.get(s).LightningSpellAttack() - villain.get(e).Defend();
                        int DamageTaken = villain.get(e).Attack() - HeroList.get(s).Defend();
                        boolean damageTakeNeg = DamageTaken < 0;

                        HeroList.get(s).useLightningSpell.remove(0);
                        if(damageTakeNeg){
                            DamageTaken = 0;
                        }
                        else if(DodgeChance<0){
                            DodgeChance = 0 ;

                        }
                        else if (Damage <0){
                            Damage = 0;
                        }
                        HeroList.get(s).HP -= DamageTaken;
                        villain.get(e).DodgeChance -= DodgeChance;
                        villain.get(e).HP -= Damage;
                        printHeader("WAR");
                        System.out.println("Dodge Damage :"+DodgeChance+" , Damage : "+Damage);

                        System.out.println("You inflicted "+DodgeChance+"  on the dodge Chance of "+villain.get(e).CharacterName + " .");

                        System.out.println("You inflicted "+Damage+"  on Enemy : "+villain.get(e).CharacterName + " .");
                        printSeparation(10);
                        System.out.println("The enemy "+ villain.get(e).CharacterName + " inflicted  "+DamageTaken +" on you .");
                        if(HeroList.get(s).HP <=0)
                        {
                            System.out.println("Player HP :"+HeroList.get(s).HP);
                            playerDeath();
                            break;
                        }
                        else if(villain.get(e).HP<=0){
                            printHeader("You defeated the "+ EnemyList.get(s).CharacterName+" !");
                            HeroList.get(s).XP += villain.get(e).XP;
                            HeroList.get(s).HP += 0.5 *HeroList.get(s).MaxHP ;
                            HeroList.get(s).ManaPoints += 0.5*HeroList.get(s).ManaPoints;
                            System.out.println("You earned the " +villain.get(e).XP+" XP!");
                            boolean additionalRest = (Math.random()*5+1 <= 2.25);

                            int MoneyEarned = (int)(Math.random()*villain.get(e).XP*100);
                            int difference = HeroList.size() - FaintedHeroList.size() ;
                            if(difference >0)
                            {
                                MoneyEarned = MoneyEarned*difference;
                            }

                            if(additionalRest){
                                HeroList.get(s).restsLeft ++ ;
                                System.out.println("You earned additional rest gifts .");

                            }
                            if(MoneyEarned> 0){
                                HeroList.get(s).gold += MoneyEarned;
                                System.out.println("You collect "+ MoneyEarned+" gold from the" + villain.get(e).CharacterName+ "'s corpse !");
                            }
                            break;
                        }
                    }
                    else{
                        System.out.println("You don't have enough Mana Points .");
                    }}
                    else{
                        System.out.println("You are not equipped with any Lightning Spell.");
                    }

                }
                else if(choice == 2){
                    if(!HeroList.get(s).equippedfireSpells.isEmpty()){
                    if(HeroList.get(s).ManaPoints > HeroList.get(s).equippedfireSpells.get(0).MPCost) {
                        HeroList.get(s).useFireSpell.add(HeroList.get(s).equippedfireSpells.get(0));
                        HeroList.get(s).equippedfireSpells.remove(0);
                        int DefenseChance = HeroList.get(s).FireSpellAttack() ;
                        int Damage = HeroList.get(s).FireSpellAttack() - villain.get(e).Defend();
                        int DamageTaken = villain.get(e).Attack() - HeroList.get(s).Defend();
                        boolean damageTakeNeg = DamageTaken < 0;
                        HeroList.get(s).useFireSpell.remove(0);
                        if(damageTakeNeg){
                            DamageTaken = 0;
                        }
                        else if(DefenseChance<0){
                            DefenseChance = 0 ;

                        }
                        else if (Damage <0){
                            Damage = 0;
                        }
                        HeroList.get(s).HP -= DamageTaken;
                        villain.get(e).Defense -= DefenseChance;
                        villain.get(e).HP -= Damage;
                        printHeader("WAR");
                        System.out.println("Dodge Damage :"+DefenseChance+" , Damage : "+Damage);

                        System.out.println("You inflicted "+DefenseChance+"  on the Defence Chance of "+villain.get(e).CharacterName + " .");

                        System.out.println("You inflicted "+Damage+"  on Enemy : "+villain.get(e).CharacterName + " .");
                        printSeparation(10);
                        System.out.println("The enemy "+ villain.get(e).CharacterName + " inflicted  "+DamageTaken +" on you .");
                        if(HeroList.get(s).HP <=0)
                        {
                            System.out.println("Player HP :"+HeroList.get(e).HP);
                            playerDeath();
                            break;
                        }
                        else if(villain.get(e).HP<=0){
                            printHeader("You defeated the "+ EnemyList.get(s).CharacterName+" !");
                            HeroList.get(s).XP += villain.get(e).XP;
                            HeroList.get(s).HP += 0.5 *HeroList.get(s).MaxHP ;
                            HeroList.get(s).ManaPoints += 0.5*HeroList.get(s).ManaPoints;
                            System.out.println("You earned the " +villain.get(e).XP+" XP!");
                            boolean additionalRest = (Math.random()*5+1 <= 2.25);
                            int MoneyEarned = (int)(Math.random()*villain.get(e).XP*100);
                            int difference = HeroList.size() - FaintedHeroList.size() ;
                            if(difference >0)
                            {
                                MoneyEarned = MoneyEarned*difference;
                            }
                            if(additionalRest){
                                HeroList.get(s).restsLeft ++ ;
                                System.out.println("You earned additional rest gifts .");

                            }
                            if(MoneyEarned> 0){
                                HeroList.get(s).gold += MoneyEarned;
                                System.out.println("You collect "+ MoneyEarned+" gold from the" + villain.get(e).CharacterName+ "'s corpse !");
                            }
                            break;
                        }
                    }
                    else{
                        System.out.println("You don't have enough Mana Points .");
                    }}
                    else{
                        System.out.println("You aren't equipped with any spell.");
                    }
                }
                else if(choice == 3){
                    if(!HeroList.get(s).equippediceSpells.isEmpty()){
                    if(HeroList.get(s).ManaPoints > HeroList.get(s).equippediceSpells.get(0).MPCost) {
                        HeroList.get(s).useIceSpell.add(HeroList.get(s).equippediceSpells.get(0));
                        HeroList.get(s).equippediceSpells.remove(0);
                        int DamageReduce = HeroList.get(s).IceSpellAttack() ;
                        int Damage = HeroList.get(s).IceSpellAttack() - villain.get(e).Defend();
                        int DamageTaken = villain.get(e).Attack() - HeroList.get(e).Defend();
                        boolean damageTakeNeg = DamageTaken < 0;

                        HeroList.get(s).useIceSpell.remove(0);
                        if(damageTakeNeg){
                            DamageTaken = 0;
                        }
                        else if(DamageReduce<0){
                            DamageReduce = 0 ;

                        }
                        else if (Damage <0){
                            Damage = 0;
                        }
                        HeroList.get(e).HP -= DamageTaken;
                        villain.get(e).Damage -= DamageReduce;
                        villain.get(e).HP -= Damage;
                        printHeader("WAR");
                        System.out.println("Dodge Damage :"+DamageReduce+" , Damage : "+Damage);

                        System.out.println("You inflicted "+DamageReduce+"  on the Damage caused by "+villain.get(e).CharacterName + " .");

                        System.out.println("You inflicted "+Damage+"  on Enemy : "+villain.get(e).CharacterName + " .");
                        printSeparation(10);
                        System.out.println("The enemy "+ villain.get(s).CharacterName + " inflicted  "+DamageTaken +" on you .");
                        if(HeroList.get(s).HP <=0)
                        {
                            System.out.println("Player HP :"+HeroList.get(s).HP);
                            playerDeath();
                            break;
                        }
                        else if(villain.get(e).HP<=0){
                            printHeader("You defeated the "+ EnemyList.get(s).CharacterName+" !");
                            HeroList.get(s).XP += villain.get(e).XP;
                            HeroList.get(s).HP += 0.5 *HeroList.get(s).MaxHP ;
                            HeroList.get(s).ManaPoints += 0.5*HeroList.get(s).ManaPoints;
                            System.out.println("You earned the " +villain.get(e).XP+" XP!");
                            boolean additionalRest = (Math.random()*5+1 <= 2.25);
                            int MoneyEarned = (int)(Math.random()*villain.get(e).XP*100);
                            int difference = HeroList.size() - FaintedHeroList.size() ;
                            if(difference >0)
                            {
                                MoneyEarned = MoneyEarned*difference;
                            }
                            if(additionalRest){
                                HeroList.get(s).restsLeft ++ ;
                                System.out.println("You earned additional rest gifts .");

                            }
                            if(MoneyEarned> 0){
                                HeroList.get(s).gold += MoneyEarned;
                                System.out.println("You collect "+ MoneyEarned+" gold from the" + villain.get(e).CharacterName+ "'s corpse !");
                            }
                            break;
                        }
                    }
                    else{
                        System.out.println("You don't have enough Mana Points .");
                    }}
                    else{
                        System.out.println("You don't have any equipped spells.");
                    }
                }

            }
            if (sc == 3){
                int Damage = HeroList.get(s).Attack() - villain.get(e).Defend();
                int DamageTaken = villain.get(e).Attack() - HeroList.get(s).Defend();
                boolean damageTakeNeg = DamageTaken < 0;
                if(damageTakeNeg){
                    Damage -= DamageTaken/2;
                    DamageTaken = 0;
                }
                else if(Damage<0){
                    Damage = 0 ;

                }
                HeroList.get(s).HP -= DamageTaken;
                villain.get(e).HP -= Damage;
                printHeader("WAR");
                System.out.println("Damage :"+Damage);

                System.out.println("You inflicted "+Damage+" damage on the "+villain.get(e).CharacterName + " .");
                printSeparation(10);
                System.out.println("The enemy "+ villain.get(s).CharacterName + " inflicted  "+DamageTaken +" on you .");
                if(HeroList.get(s).HP <=0)
                {
                    System.out.println("Player HP :"+HeroList.get(s).HP);
                    playerDeath();
                    break;
                }
                else if(villain.get(e).HP<=0){
                    printHeader("You defeated the "+ EnemyList.get(s).CharacterName+" !");
                    HeroList.get(s).XP += villain.get(e).XP;
                    HeroList.get(s).HP += 0.5 *HeroList.get(s).MaxHP ;
                    HeroList.get(s).ManaPoints += 0.5*HeroList.get(s).ManaPoints;
                    System.out.println("You earned the " +villain.get(e).XP+" XP!");
                    boolean additionalRest = (Math.random()*5+1 <= 2.25);
                    int MoneyEarned = (int)(Math.random()*villain.get(e).XP*100);
                    int difference = HeroList.size() - FaintedHeroList.size() ;
                    if(difference >0)
                    {
                        MoneyEarned = MoneyEarned*difference;
                    }
                    if(additionalRest){
                        HeroList.get(s).restsLeft ++ ;
                        System.out.println("You earned additional rest gifts .");

                    }
                    if(MoneyEarned> 0){
                        HeroList.get(s).gold += MoneyEarned;
                        System.out.println("You collect "+ MoneyEarned+" gold from the" + villain.get(e).CharacterName+ "'s corpse !");
                    }
                    break;
                }

            }
                if(sc == 4 ){
//                    if(player.pots >0 && player.HP < player.MaxHP){
//                        printHeader("Do you want to consume a potion? ("+player.pots+" left)." );
//                        System.out.println("(1) Yes");
//                        System.out.println("(2) No ");
//                        int choice =  UserInput(" -- >",2);
//                        if(choice == 1){
//                            player.HP = player.MaxHP;
//                            printHeader("You drank a magic potion and restored your health.");
//
//                        }
//                    }
//                    else{
//                        printHeader("You don't have any potions or maybe you have full health restored already . ");
//
//                    }
                        if(!HeroList.get(s).equippedPotions.isEmpty()){
                            printHeader("Do you want to consume the potion equipped by you ? ");
                            System.out.println("(1) Yes");
                            System.out.println("(2) No");
                            int choice = UserInput("-->" , 2);
                            if(choice == 1){
                                if(HeroList.get(s).equippedPotions.get(0).Attribute == "Health"){
                                    HeroList.get(s).HP += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                                    System.out.println("Your Health Increased After consuming the potion.");
                                }
                                else if(HeroList.get(s).equippedPotions.get(0).Attribute == "Strength"){
                                    HeroList.get(s).StrengthValue += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                                    System.out.println("Your Strength Increased After consuming the potion.");

                                }
                                else if(HeroList.get(s).equippedPotions.get(0).Attribute == "Mana"){
                                    HeroList.get(s).ManaPoints += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                                    System.out.println("Your MP Increased After consuming the potion.");
                                }
                                else if(HeroList.get(s).equippedPotions.get(0).Attribute == "Agility"){
                                    HeroList.get(s).agileValue += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                                    System.out.println("Your Agility Increased After consuming the potion.");

                                }
                                else if(HeroList.get(s).equippedPotions.get(0).Attribute == "Any"){
                                        Random b = new Random();
                                        int randomChoice = b.nextInt(4)+1;
                                        if(randomChoice == 1){
                                            HeroList.get(s).HP += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                                            System.out.println("Your Health Increased After consuming the potion.");
                                        }
                                    if(randomChoice == 2){

                                        HeroList.get(s).StrengthValue += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                                        System.out.println("Your Strength Increased After consuming the potion.");
                                    }
                                    if(randomChoice == 3){
                                        HeroList.get(s).ManaPoints += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                                        System.out.println("Your MP Increased After consuming the potion.");
                                    }
                                    if(randomChoice == 4){
                                        HeroList.get(s).agileValue += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                                        System.out.println("Your Agility Increased After consuming the potion.");

                                    }

                                }
                                else if(HeroList.get(s).equippedPotions.get(0).Attribute == "All"){

                                    HeroList.get(s).HP += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                                    HeroList.get(s).StrengthValue += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                                    HeroList.get(s).ManaPoints += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                                    HeroList.get(s).agileValue += HeroList.get(s).equippedPotions.get(0).attributeIncrease;
                                    System.out.println("All your abilities Increased After consuming the potion.");
                                }
                            }
                        }

                }
                else if(sc == 8){
                    if (HeroList.get(s).level != 4) {
                        if (Math.random() * 10 + 1 <= 3.5) {
                            printHeader("You ran away from the " + villain.get(e).CharacterName + " ! ");
                            break;
                        } else {
                            printHeader("You didn't manage to escape from the clutches of the monster . Sorry ! ");
                             int DamageTaken = villain.get(e).Attack();
                            System.out.println(" In your Hurry , you took about " + DamageTaken + " Damage !");
                            if (HeroList.get(s).HP <= 0) {
                                playerDeath();
                            }

                        }
                    } else {
                        printHeader("You cannot escape this . ");
                    }
                }
                else if(sc == 5){
                    System.out.println("Weapons : ");
                    for(int i = 0 ; i < HeroList.get(s).weaponInventory.size();i++){
                    System.out.println(HeroList.get(s).weaponInventory.get(i).Name+" Damage Value"+HeroList.get(s).weaponInventory.get(i).damageValue+ " Number of Hands Required"+HeroList.get(s).weaponInventory.get(i).noHands+" Level"+HeroList.get(s).weaponInventory.get(i).level);

                    }
                    System.out.println("Armors : ");
                    for(int i = 0 ; i < HeroList.get(s).armorInventory.size();i++){
                        System.out.println(HeroList.get(s).armorInventory.get(i).Name+" Level "+HeroList.get(s).armorInventory.get(i).level+" Damage Reduction : "+HeroList.get(s).armorInventory.get(i).damageReductionValue);

                    }
                    System.out.println("Spells : ");
                    System.out.println("Lightning Spells : ");
                    for(int i = 0 ; i < HeroList.get(s).lightningSpellsInventory.size();i++){
                        System.out.println(HeroList.get(s).lightningSpellsInventory.get(i).Name+" Level : "+HeroList.get(s).lightningSpellsInventory.get(i).level+" Damage : "+HeroList.get(s).lightningSpellsInventory.get(i).Damage+" MP Cost :"+HeroList.get(s).lightningSpellsInventory.get(i).MPCost);

                    }
                    System.out.println("Fire Spells : ");
                    for(int i = 0 ; i < HeroList.get(s).fireSpellsInventory.size();i++){
                        System.out.println(HeroList.get(s).fireSpellsInventory.get(i).Name+" Level : "+HeroList.get(s).fireSpellsInventory.get(i).level+" Damage : "+HeroList.get(s).fireSpellsInventory.get(i).Damage+" MP Cost :"+HeroList.get(s).fireSpellsInventory.get(i).MPCost);

                    }System.out.println("Ice Spells : ");

                    for(int i = 0 ; i < HeroList.get(s).iceSpellsInventory.size();i++){
                        System.out.println(HeroList.get(s).iceSpellsInventory.get(i).Name+" Level : "+HeroList.get(s).iceSpellsInventory.get(i).level+" Damage : "+HeroList.get(s).iceSpellsInventory.get(i).Damage+" MP Cost :"+HeroList.get(s).iceSpellsInventory.get(i).MPCost);

                    }


                }
                else if (sc==7){
                    if(!HeroList.get(s).weaponInventory.isEmpty()){

                    for(int k = 0 ; k< HeroList.get(s).weaponInventory.size();k++){
                        System.out.println(HeroList.get(s).weaponInventory.get(k).Name+" Damage Value"+HeroList.get(s).weaponInventory.get(k).damageValue+ " Number of Hands Required"+HeroList.get(s).weaponInventory.get(k).noHands+" Level"+HeroList.get(s).weaponInventory.get(k).level);

                    }
                    System.out.println("Equip Your Weapon from Inventory");
                    int choice = UserInput("Choose your Weapon Wisely" , HeroList.get(s).weaponInventory.size());
                        System.out.println(HeroList.get(s).inventory);

                    equipWeapon(HeroList.get(s).weaponInventory,HeroList.get(s).equippedWeapon,HeroList.get(s).inventory,weapons.noHands,choice-1);
                        if(!HeroList.get(s).equippedWeapon.isEmpty()){
                            if(isEquipped){
                                HeroList.get(s).inventory += weapons.noHands;
                            }
                        }
                        System.out.println(HeroList.get(s).inventory);
                        System.out.println("It finished");
                    System.out.println(HeroList.get(s).CharacterName+" equipped "+HeroList.get(s).equippedWeapon.get(0).Name);}
                    else{
                        System.out.println("You don't have any weapons in your inventory.");
                    }
                }
                else if (sc==9){
                    if(!HeroList.get(s).armorInventory.isEmpty()){
                        int k = 0;
                        while(k < HeroList.get(s).armorInventory.size()){
                            System.out.println(HeroList.get(s).armorInventory.get(k).Name+" Level "+HeroList.get(s).armorInventory.get(k).level+" Damage Reduction : "+HeroList.get(s).armorInventory.get(k).damageReductionValue);
                            k++;
                        }
                        System.out.println("Equip Your Armor from Inventory");
                        int choice = UserInput("Choose your Armor Wisely" , HeroList.get(s).armorInventory.size());

                        equipArmor(HeroList.get(s).armorInventory,HeroList.get(s).equippedArmor,choice-1);

                        System.out.println("It finished");
                        System.out.println(HeroList.get(s).CharacterName+" equipped "+HeroList.get(s).equippedArmor.get(0).Name);}
                    else{
                        System.out.println("You don't have any Armor in your inventory.");
                    }
                }
                else if (sc==11){
                    System.out.println("Choose Type of Spell");
                    System.out.println("(1) Lightning Spell");
                    System.out.println("(2) Fire Spell");
                    System.out.println("(3) Ice Spell");
                    int newChoice = UserInput("->",3);
                    if(newChoice == 1){
                    if(!HeroList.get(s).lightningSpellsInventory.isEmpty()){
                        int k = 0;
                        while(k < HeroList.get(s).lightningSpellsInventory.size()){
                            System.out.println(HeroList.get(s).lightningSpellsInventory.get(k).Name+" Level : "+HeroList.get(s).lightningSpellsInventory.get(k).level+" Damage : "+HeroList.get(s).lightningSpellsInventory.get(k).Damage+" MP Cost :"+HeroList.get(s).lightningSpellsInventory.get(k).MPCost);
                            k++;
                        }
                        System.out.println("Equip Your Lightning Spell from Inventory");
                        int choice = UserInput("Choose your Spell Wisely" , HeroList.get(s).lightningSpellsInventory.size());

                        equipLightningSpells(HeroList.get(s).lightningSpellsInventory,HeroList.get(s).equippedlightningSpells,choice-1);

                        System.out.println("It finished");
                        System.out.println(HeroList.get(s).CharacterName+" equipped "+HeroList.get(s).equippedlightningSpells.get(0).Name);}
                    else{
                        System.out.println("You don't have any Lightning Spells in your inventory.");
                    }
                    }
                    else if(newChoice == 2){
                        if(!HeroList.get(s).fireSpellsInventory.isEmpty()){
                            int k = 0;
                            while(k < HeroList.get(s).fireSpellsInventory.size()){
                                System.out.println(HeroList.get(s).fireSpellsInventory.get(k).Name+" Level : "+HeroList.get(s).fireSpellsInventory.get(k).level+" Damage : "+HeroList.get(s).fireSpellsInventory.get(k).Damage+" MP Cost :"+HeroList.get(k).fireSpellsInventory.get(k).MPCost);
                                k++;
                            }
                            System.out.println("Equip Your Fire Spell from Inventory");
                            int choice = UserInput("Choose your Spell Wisely" , HeroList.get(s).fireSpellsInventory.size());

                            equipFireSpells(HeroList.get(s).fireSpellsInventory,HeroList.get(s).equippedfireSpells,choice-1);

                            System.out.println("It finished");
                            System.out.println(HeroList.get(s).CharacterName+" equipped "+HeroList.get(s).equippedfireSpells.get(0).Name);}
                        else{
                            System.out.println("You don't have any Fire Spells in your inventory.");
                        }


                    }
                    else if(newChoice == 3){
                        if(!HeroList.get(s).iceSpellsInventory.isEmpty()){
                            int k = 0;
                            while(k < HeroList.get(s).iceSpellsInventory.size()){
                                System.out.println(HeroList.get(s).iceSpellsInventory.get(k).Name+" Level : "+HeroList.get(s).iceSpellsInventory.get(k).level+" Damage : "+HeroList.get(s).iceSpellsInventory.get(k).Damage+" MP Cost :"+HeroList.get(s).iceSpellsInventory.get(k).MPCost);
                                k++;
                            }
                            System.out.println("Equip Your Ice Spells from Inventory");
                            int choice = UserInput("Choose your Ice Spells Wisely" , HeroList.get(s).iceSpellsInventory.size());

                            equipIceSpells(HeroList.get(s).iceSpellsInventory,HeroList.get(s).equippediceSpells,choice-1);

                            System.out.println("It finished");
                            System.out.println(HeroList.get(s).CharacterName+" equipped "+HeroList.get(s).equippediceSpells.get(0).Name);}
                        else{
                            System.out.println("You don't have any Ice Spells in your inventory.");
                        }
                    }
                }
                else if (sc==10){
                    if(!HeroList.get(s).equippedArmor.isEmpty()) {
                        int k = 0;int l = 0;
                        while (k < HeroList.get(s).armorInventory.size()) {
                            System.out.println("Armor Inventory : " + HeroList.get(s).armorInventory.get(k).Name);
                            k++;
                        }
                        while (l < HeroList.get(s).equippedArmor.size()) {
                            System.out.println("Armor Equipped : " + HeroList.get(s).equippedArmor.get(l).Name);
                            l++;
                        }
                        int choice = UserInput("Choose the Armor you want to drop", HeroList.get(s).equippedArmor.size());
                        dropArmorToInventory(HeroList.get(s).armorInventory, HeroList.get(s).equippedArmor, choice - 1);


                        System.out.println(HeroList.get(s).CharacterName + " dropped " + HeroList.get(s).armorInventory.get(0).Name);
                        for(int i = 0 ; i < HeroList.get(s).armorInventory.size();i++){
                        System.out.println("Player Armor Inventory : "+HeroList.get(s).armorInventory.get(i).Name);
                        }
                    }
                    else{
                        System.out.println("You aren't wearing any armor .");
                    }

                }else if (sc==12){
                    System.out.println("Choose Type of Spell");
                    System.out.println("(1) Lightning Spell");
                    System.out.println("(2) Fire Spell");
                    System.out.println("(3) Ice Spell");
                    int newChoice = UserInput("->",3);
                    if(newChoice == 1){
                        if(!HeroList.get(s).equippedlightningSpells.isEmpty()) {
                            int k = 0;int l = 0;
                            while (k < HeroList.get(s).lightningSpellsInventory.size()) {
                                System.out.println("Lightning Spell Inventory : " + HeroList.get(s).lightningSpellsInventory.get(k).Name);
                                k++;
                            }
                            while (l < HeroList.get(s).equippedlightningSpells.size()) {
                                System.out.println("Lightning Spells Equipped : " + HeroList.get(s).equippedlightningSpells.get(l).Name);
                                l++;
                            }
                            int choice = UserInput("Choose the Lightning Spells you want to drop", HeroList.get(s).equippedlightningSpells.size());
                            dropLightningSpellToInventory(HeroList.get(s).lightningSpellsInventory, HeroList.get(s).equippedlightningSpells, choice - 1);


                            System.out.println(HeroList.get(s).CharacterName + " dropped " + HeroList.get(s).lightningSpellsInventory.get(0).Name);
                        }
                        else{
                            System.out.println("You don't have any Lightning Spells .");
                        }

                    }else if(newChoice == 2){
                        if(!HeroList.get(s).equippedfireSpells.isEmpty()) {
                            int k = 0;int l = 0;
                            while (k < HeroList.get(s).fireSpellsInventory.size()) {
                                System.out.println("Fire Spells Inventory : " + HeroList.get(s).fireSpellsInventory.get(k).Name);
                                k++;
                            }
                            while (l < HeroList.get(s).equippedArmor.size()) {
                                System.out.println("Fire Spells Equipped : " + HeroList.get(s).equippedfireSpells.get(l).Name);
                                l++;
                            }
                            int choice = UserInput("Choose the Fire Spell you want to drop", HeroList.get(s).equippedfireSpells.size());
                            dropFireSpellToInventory(HeroList.get(s).fireSpellsInventory, HeroList.get(s).equippedfireSpells, choice - 1);


                            System.out.println(HeroList.get(s).CharacterName + " dropped " + HeroList.get(s).fireSpellsInventory.get(0).Name);
                        }
                        else{
                            System.out.println("You don't have any Fire Spells ");
                        }


                    }else if(newChoice == 3)
                    {
                        if(!HeroList.get(s).equippediceSpells.isEmpty()) {
                            int k = 0;int l = 0;
                            while (k < HeroList.get(s).iceSpellsInventory.size()) {
                                System.out.println("Ice Spell Inventory : " + HeroList.get(s).iceSpellsInventory.get(k).Name);
                                k++;
                            }
                            while (l < HeroList.get(s).equippediceSpells.size()) {
                                System.out.println("Ice Spells Equipped : " + HeroList.get(s).equippediceSpells.get(l).Name);
                                l++;
                            }
                            int choice = UserInput("Choose the Ice Spell you want to drop", HeroList.get(s).equippediceSpells.size());
                            dropIceSpellToInventory(HeroList.get(s).iceSpellsInventory, HeroList.get(s).equippediceSpells, choice - 1);


                            System.out.println(HeroList.get(s).CharacterName + " dropped " + HeroList.get(s).iceSpellsInventory.get(0).Name);
                        }
                        else{
                            System.out.println("You don't have any Ice spells.");
                        }

                    }

                }
                else if (sc==6){
                    if(!HeroList.get(s).equippedWeapon.isEmpty()) {
                        int k = 0;
                        int l= 0;
                        while (k < HeroList.get(s).weaponInventory.size()) {
                            System.out.println("Weapon Inventory : " + HeroList.get(s).weaponInventory.get(k).Name);
                            k++;
                        }
                        while (l < HeroList.get(s).equippedWeapon.size()) {
                            System.out.println("Weapon Equipped : " + HeroList.get(s).equippedWeapon.get(l).Name);
                            l++;
                        }
                        int choice = UserInput("Choose the Weapon you want to drop", HeroList.get(s).equippedWeapon.size());
//                        for (int i = 0; i < player.equippedWeapon.size(); i++) {
//                            player.inventory += player.equippedWeapon.get(i).noHands;
//                        }
                        dropWeaponToInventory(HeroList.get(s).weaponInventory, HeroList.get(s).equippedWeapon, HeroList.get(s).inventory, weapons.noHands, choice - 1);
                        if(isDropped){

                        if (HeroList.get(s).inventory == 1) {
                            if (weapons.noHands == 1) {
                                HeroList.get(s).inventory -= 1;
                                System.out.println("III");
                            }
                        }
                            if (HeroList.get(s).inventory == 2) {
                                if (weapons.noHands == 1) {
                                    HeroList.get(s).inventory -= 1;
                                    System.out.println("I");
                                } else if (weapons.noHands == 2) {
                                    HeroList.get(s).inventory -= 2;
                                    System.out.println("II");
                                }
                            }



                        }
                        System.out.println("Hands Occupied " + HeroList.get(s).inventory);

                        System.out.println(HeroList.get(s).CharacterName + " dropped " + HeroList.get(s).weaponInventory.get(0).Name);
                    }
                else{
                        System.out.println("You don't have any weapon in your hand.");
                    }

                } else if (sc == 13) {
                    if(!HeroList.get(s).potionsInventory.isEmpty()){
                        int k = 0;
                        while(k < HeroList.get(s).potionsInventory.size()){
                            System.out.println("Potion Inventory : "+HeroList.get(s).potionsInventory.get(k).Name);
                            k++;
                        }
                        System.out.println("Equip Your Potion from Inventory");
                        int choice = UserInput("Choose your Potion Wisely" , HeroList.get(s).armorInventory.size());

                        equipPotions(HeroList.get(s).potionsInventory,HeroList.get(s).equippedPotions,choice-1);

                        System.out.println("It finished");
                        System.out.println(HeroList.get(s).CharacterName+" equipped "+HeroList.get(s).equippedPotions.get(0).Name);}
                    else{
                        System.out.println("You don't have any Potions in your inventory.");
                    }
                }
                else if(sc ==14){
                    if(!HeroList.get(s).equippedPotions.isEmpty()) {
                        int k = 0;int l = 0;
                        while (k < HeroList.get(s).potionsInventory.size()) {
                            System.out.println("Potion Inventory : " + HeroList.get(s).potionsInventory.get(k).Name);
                            k++;
                        }
                        while (l < HeroList.get(s).equippedArmor.size()) {
                            System.out.println("Potion Equipped : " + HeroList.get(s).potionsInventory.get(l).Name);
                            l++;
                        }
                        int choice = UserInput("Choose the Potion you want to drop", HeroList.get(s).equippedPotions.size());
                        dropPotionToInventory(HeroList.get(s).potionsInventory, HeroList.get(s).equippedPotions, choice - 1);


                        System.out.println(HeroList.get(s).CharacterName + " dropped " + HeroList.get(s).potionsInventory.get(0).Name);
                        for(int i = 0 ; i < HeroList.get(s).potionsInventory.size();i++){
                            System.out.println("Player Potion Inventory : "+HeroList.get(s).potionsInventory.get(i).Name);
                        }
                    }
                    else{
                        System.out.println("You don't have any potions .");
                    }
                }
                else if(sc ==15){
                    System.out.println("Player Info :");
                    System.out.println(" HP : "+HeroList.get(s).HP + " , Level : "+ HeroList.get(s).level+ " MP : "+HeroList.get(s).ManaPoints);
                    if(!HeroList.get(s).equippedWeapon.isEmpty()){
                        System.out.println("Currently Equipped Weapons : ");
                        for(int z = 0 ;z < HeroList.get(s).equippedWeapon.size();z++){
                            System.out.println("("+z+") "+HeroList.get(s).equippedWeapon.get(z).Name);
                        }
                    }else{
                        System.out.println("You don't have any weapons currently Equipped.");
                    }
                    if(!HeroList.get(s).equippedlightningSpells.isEmpty()){
                        System.out.println("Currently Equipped Lightning Spells : ");
                        for(int z = 0 ;z < HeroList.get(s).equippedlightningSpells.size();z++){
                            System.out.println("("+z+") "+HeroList.get(s).equippedlightningSpells.get(z).Name);
                        }
                    }else{
                        System.out.println("You don't have any Lightning Spells currently Equipped.");
                    }
                    if(!HeroList.get(s).equippedfireSpells.isEmpty()){
                        System.out.println("Currently Equipped Fire Spells : ");
                        for(int z = 0 ;z < HeroList.get(s).equippedfireSpells.size();z++){
                            System.out.println("("+z+") "+HeroList.get(s).equippedfireSpells.get(z).Name);
                        }
                    }else{
                        System.out.println("You don't have any Fire Spells currently Equipped.");
                    }
                    if(!HeroList.get(s).equippediceSpells.isEmpty()){
                        System.out.println("Currently Equipped Ice Spells : ");
                        for(int z = 0 ;z < HeroList.get(s).equippediceSpells.size();z++){
                            System.out.println("("+z+") "+HeroList.get(s).equippediceSpells.get(z).Name);
                        }
                    }else{
                        System.out.println("You don't have any Ice Spells currently Equipped.");
                    }
                    if(!HeroList.get(s).equippedPotions.isEmpty()){
                        System.out.println("Currently Equipped Potions : ");
                        for(int z = 0 ;z < HeroList.get(s).equippedPotions.size();z++){
                            System.out.println("("+z+") "+HeroList.get(s).equippedPotions.get(z).Name);
                        }
                    }else{
                        System.out.println("You don't have any Potions currently Equipped.");
                    }
                    if(!HeroList.get(s).equippedArmor.isEmpty()){
                        System.out.println("Currently Equipped Armor : ");
                        for(int z = 0 ;z < HeroList.get(s).equippedArmor.size();z++){
                            System.out.println("("+z+") "+HeroList.get(s).equippedArmor.get(z).Name);
                        }
                    }else{
                        System.out.println("You don't have any armor currently Equipped.");
                    }


                }
                else if(sc ==16){
                    System.out.println("Villain Information List : ");
                    for(int p=0 ; p <VillainList.size() ; p++){
                    System.out.println("Name : "+VillainList.get(p).CharacterName+" XP : "+VillainList.get(p).XP+" HP: "+VillainList.get(p).HP+" Damage : "+VillainList.get(p).Damage+" Defense" +VillainList.get(p).Defense+" Dodge Chance "+VillainList.get(p).DodgeChance);
                }
                }
        }
            }
        if(DeadVillainList.size() == NumberOfHeroes) {

            if (!HeroList.isEmpty()) {
                if(!FaintedHeroList.isEmpty()){
                for (int i = 0; i < FaintedHeroList.size(); i++) {
                    System.out.println("Heroes Fainted" + FaintedHeroList.get(i).CharacterName);
                    FaintedHeroList.get(i).HP = FaintedHeroList.get(i).MaxHP / 2;
                    HeroList.add(FaintedHeroList.get(i));
                    FaintedHeroList.remove(i);

                }
                for (int j = 0; j < HeroList.size(); j++) {
                    System.out.println("Heroes  " + HeroList.get(j).CharacterName + "HP : " + HeroList.get(j).HP);
                }
            }}
        }
        }
   }



    private static void dropPotionToInventory(ArrayList<Potions> potionsInventory, ArrayList<Potions> equippedPotions, int i) {
        System.out.println("Potions : In Equipped Inventory");
        for(int q = 0 ; q< equippedPotions.size();q++){
            System.out.println(equippedPotions.get(q).Name);

        }

        if(!equippedPotions.isEmpty()) {

            potionsInventory.add(equippedPotions.get(i));
            equippedPotions.remove(equippedPotions.get(i));
        }
        else{
            System.out.println("You have no Potions .");
        }
    }

    private static void dropIceSpellToInventory(ArrayList<IceSpells> iceSpellsInventory, ArrayList<IceSpells> equippediceSpells, int i) {
        System.out.println("Ice Spells : In Equipped Inventory");
        for(int q = 0 ; q< equippediceSpells.size();q++){
            System.out.println(equippediceSpells.get(q).Name);

        }

        if(!equippediceSpells.isEmpty()) {

            iceSpellsInventory.add(equippediceSpells.get(i));
            equippediceSpells.remove(equippediceSpells.get(i));
        }
        else{
            System.out.println("You have no Ice Spells .");
        }
    }

    private static void dropFireSpellToInventory(ArrayList<FireSpells> fireSpellsInventory, ArrayList<FireSpells> equippedfireSpells, int i) {
        System.out.println("Lightning Spells : In Equipped Inventory");
        for(int q = 0 ; q< equippedfireSpells.size();q++){
            System.out.println(equippedfireSpells.get(q).Name);

        }

        if(!equippedfireSpells.isEmpty()) {

            fireSpellsInventory.add(equippedfireSpells.get(i));
            equippedfireSpells.remove(equippedfireSpells.get(i));
        }
        else{
            System.out.println("You have no Fire Spells .");
        }

    }

    private static void dropLightningSpellToInventory(ArrayList<LightningSpells> lightningSpellsInventory, ArrayList<LightningSpells> equippedlightningSpells, int i) {
        System.out.println("Lightning Spells : In Equipped Inventory");
        for(int q = 0 ; q< equippedlightningSpells.size();q++){
            System.out.println(equippedlightningSpells.get(q).Name);

        }

        if(!equippedlightningSpells.isEmpty()) {

            lightningSpellsInventory.add(equippedlightningSpells.get(i));
            equippedlightningSpells.remove(equippedlightningSpells.get(i));
        }
        else{
            System.out.println("You have no Lightning Spells .");
        }
   }

    private static void dropArmorToInventory(ArrayList<Armor> armorInventory, ArrayList<Armor> equippedArmor, int i) {
        System.out.println("Armors : In Equipped Inventory");
        for(int q = 0 ; q< equippedArmor.size();q++){
            System.out.println(equippedArmor.get(q).Name);

        }

        if(!equippedArmor.isEmpty()) {

            armorInventory.add(equippedArmor.get(i));
            equippedArmor.remove(equippedArmor.get(i));
        }
            else{
                System.out.println("You have no armor , wear one .");
            }
        }



    public static void RandomEncounter(){
        int collisionPtr = (int)(Math.random()*Collision.length);
        if(Collision[collisionPtr].equalsIgnoreCase("Battle")){
            RandomCollision();

            newBoard.userMove(size,'H');


        }
        else if(Collision[collisionPtr].equalsIgnoreCase("Rest")){
            takeRest();
            newBoard.userMove(size,'H');
        }
//        else if(Collision[collisionPtr].equalsIgnoreCase("Shop")){
////        shop();
//            populateMarket();
//            ShopFromMarket();
//
//            newBoard.userMove(8,'H');
//        }
    }

    private static void characterStats() {
        printHeader("CHARACTER INFORMATION : ");
        System.out.println(player.CharacterName+ "\t HP:"+ player.HP+"/"+player.MaxHP);
        printSeparation(20);
        System.out.println("XP:" + player.XP+"\tGold:"+player.gold);
        printSeparation(20);
        System.out.println("Number of Potions : "+player.pots);
        printSeparation(20);
        if(player.AttackUpgradePtr > 0){
            System.out.println("Offensive Trait : "+ player.AttackUpgrades[player.AttackUpgradePtr-1]);
            printSeparation(20);

        }
        else if(player.DefendUpgradePtr > 0){
            System.out.println("Defensive Trait : "+ player.DefendUpgrades[player.DefendUpgradePtr-1]);

        }

    }

    private static void printMenu() {
        printHeader("MENU FOR GAME :");
        printHeader(places[place]);
        System.out.println("Choose some Action. ");
        printSeparation(20);
        System.out.println("(1) Continue The Game.");
        System.out.println("(2) Character Statistics .");
        System.out.println("(3) Leave the Game .");
        System.out.println("(4) Print the map .");
    }
    public static void ShopFromMarket(){
       populateMarket();
       if(!HeroList.isEmpty()) {
           for (int f = 0; f < HeroList.size(); f++) {


               printHeader("You have arrived at the Market . Do you want to Enter ?");
               System.out.println("(1) Yes");
               System.out.println("(2) No");
               int Choice = UserInput("--> ", 2);
               if (Choice == 1) {
                   System.out.println("Do you want to Buy Or Sell");
                   System.out.println("(1) BUY");
                   System.out.println("(2) SELL");
                   int newChoice = UserInput("-->", 2);
                   if (newChoice == 1) {
                       System.out.println("Market Inventory . Choose the Market from which you want to buy :");
                       System.out.println("(1) Weapon Market");
                       System.out.println("(2) Lightning Spell Market");
                       System.out.println("(3) Fire Spell Market");
                       System.out.println("(4) Ice Spell Market");
                       System.out.println("(5) Armor Market");
                       System.out.println("(6) Potion Market");
                       int marketChoice = UserInput("-->", 6);
                       if (marketChoice == 1) {
                           if (!market.WeaponMarket.isEmpty()) {
                               System.out.println("Weapon Market :");
                               for (int i = 0; i < market.WeaponMarket.size(); i++) {
                                   System.out.println("(" + (i + 1) + ")" + market.WeaponMarket.get(i).Name+" Price :"+market.WeaponMarket.get(i).price+" Damage Value :"+market.WeaponMarket.get(i).damageValue+ " Number of Hands Required :"+market.WeaponMarket.get(i).noHands+" Level :"+market.WeaponMarket.get(i).level);

                               }
                               System.out.println("Choose one option to buy :");
                               int WeaponChoice = UserInput("-->", market.WeaponMarket.size());
                               BuyWeaponFromMarket(HeroList.get(f),WeaponChoice - 1);
                               for (int i = 0; i < HeroList.get(f).weaponInventory.size(); i++) {
                                   System.out.println("Weapons in your inventory now : " + HeroList.get(f).weaponInventory.get(i).Name);
                               }
                           } else {
                               System.out.println("Market Empty Today ! Check Another");
                               ShopFromMarket();
                           }
                       }
                       if (marketChoice == 2) {
                           if (!market.LightningSpellsMarket.isEmpty()) {
                               System.out.println("Lightning Spell Market :");
                               for (int i = 0; i < market.LightningSpellsMarket.size(); i++) {
                                   System.out.println("(" + (i + 1) + ")" + market.LightningSpellsMarket.get(i).Name+" Price : "+market.LightningSpellsMarket.get(i).price+" Level : "+market.LightningSpellsMarket.get(i).level+" Damage : "+market.LightningSpellsMarket.get(i).Damage+" MP Cost :"+market.LightningSpellsMarket.get(i).MPCost);

                               }
                               System.out.println("Choose one option to buy :");
                               int WeaponChoice = UserInput("-->", market.LightningSpellsMarket.size());
                               BuyLightningSpellFromMarket(HeroList.get(f),WeaponChoice - 1);

                           } else {

                               System.out.println("Market Empty Today ! Check Another");
                               ShopFromMarket();
                           }
                       }
                       if (marketChoice == 3) {
                           if (!market.FireSpellsMarket.isEmpty()) {
                               System.out.println("Fire Market :");
                               for (int i = 0; i < market.FireSpellsMarket.size(); i++) {
                                   System.out.println("(" + (i + 1) + ")" + market.FireSpellsMarket.get(i).Name+" Price : "+market.FireSpellsMarket.get(i).price+" Level : "+market.FireSpellsMarket.get(i).level+" Damage : "+market.FireSpellsMarket.get(i).Damage+" MP Cost :"+market.FireSpellsMarket.get(i).MPCost);

                               }
                               System.out.println("Choose one option to buy :");
                               int WeaponChoice = UserInput("-->", market.FireSpellsMarket.size());
                               BuyFireSpellFromMarket(HeroList.get(f),WeaponChoice - 1);
                           } else {

                               System.out.println("Market Empty Today ! Check Another");
                               ShopFromMarket();
                           }
                       }
                       if (marketChoice == 4) {
                           if (!market.IceSpellsMarket.isEmpty()) {
                               System.out.println("Ice Market :");
                               for (int i = 0; i < market.IceSpellsMarket.size(); i++) {
                                   System.out.println("(" + (i + 1) + ")" + market.IceSpellsMarket.get(i).Name+" Price : "+market.IceSpellsMarket.get(i).price+" Level : "+market.IceSpellsMarket.get(i).level+" Damage : "+market.IceSpellsMarket.get(i).Damage+" MP Cost :"+market.IceSpellsMarket.get(i).MPCost);

                               }
                               System.out.println("Choose one option to buy :");
                               int WeaponChoice = UserInput("-->", market.IceSpellsMarket.size());
                               BuyIceSpellFromMarket(HeroList.get(f),WeaponChoice - 1);
                           } else {

                               System.out.println("Market Empty Today ! Check Another");
                               ShopFromMarket();
                           }
                       }
                       if (marketChoice == 5) {
                           if (!market.ArmorMarket.isEmpty()) {
                               System.out.println("Armor Market :");
                               for (int i = 0; i < market.ArmorMarket.size(); i++) {
                                   System.out.println("(" + (i + 1) + ")" + market.ArmorMarket.get(i).Name+market.ArmorMarket.get(i).Name+" Price"+market.ArmorMarket.get(i).price+" Level "+market.ArmorMarket.get(i).level+" Damage Reduction : "+market.ArmorMarket.get(i).damageReductionValue);

                               }
                               System.out.println("Choose one option to buy :");
                               int WeaponChoice = UserInput("-->", market.ArmorMarket.size());
                               BuyArmorFromMarket(WeaponChoice - 1);
                           } else {

                               System.out.println("Market Empty Today ! Check Another");
                               ShopFromMarket();
                           }
                       }
                       if (marketChoice == 6) {
                           if (!market.PotionsMarket.isEmpty()) {
                               System.out.println("Potion Market :");
                               for (int i = 0; i < market.PotionsMarket.size(); i++) {
                                   System.out.println("(" + (i + 1) + ")" + market.PotionsMarket.get(i).Name);
                               }
                               System.out.println("Choose one option to buy :");
                               int WeaponChoice = UserInput("-->", market.PotionsMarket.size());
                               BuyPotionsFromMarket(HeroList.get(f),WeaponChoice - 1);
                           } else {

                               System.out.println("Market Empty Today ! Check Another");
                               ShopFromMarket();
                           }
                       }

                   } else if (newChoice == 2) {
                       System.out.println("Choose From Which Inventory You want to sell : ");
                       System.out.println("(1) Weapon Inventory");
                       System.out.println("(2) Lightning Spell Inventory");
                       System.out.println("(3) Fire Spell Inventory");
                       System.out.println("(4) Ice Spell Inventory");
                       System.out.println("(5) Armor Inventory");
                       System.out.println("(5) Potions Inventory");

                       int InventoryChoice = UserInput("-->", 6);
                       if (InventoryChoice == 1) {
                           if (!HeroList.get(f).weaponInventory.isEmpty()) {
                               System.out.println("Welcome to your Weapon Inventory . Your weapons are listed below :");
                               for (int i = 0; i < HeroList.get(f).weaponInventory.size(); i++) {
                                   System.out.println("(" + (i + 1) + ")" + HeroList.get(f).weaponInventory.get(i).Name);
                               }
                               int weaponChoice = UserInput("-->", HeroList.get(f).weaponInventory.size());
                               sellWeaponfromInventory(HeroList.get(f),weaponChoice - 1);
                           } else {

                               System.out.println("Inventory Empty Today ! Check Another");
                               ShopFromMarket();
                           }
                       } else if (InventoryChoice == 2) {
                           if (!HeroList.get(f).lightningSpellsInventory.isEmpty()) {
                               System.out.println("Welcome to your Lightning Spell Inventory . Your Spells are listed below :");
                               for (int i = 0; i < HeroList.get(f).lightningSpellsInventory.size(); i++) {
                                   System.out.println("(" + (i + 1) + ")" + HeroList.get(f).lightningSpellsInventory.get(i).Name);
                               }
                               int lightningSpellChoice = UserInput("-->", HeroList.get(f).lightningSpellsInventory.size());
                               sellLightningSpellsfromInventory(HeroList.get(f),lightningSpellChoice - 1);
                           } else {

                               System.out.println("Inventory Empty Today ! Check Another");
                               ShopFromMarket();
                           }

                       } else if (InventoryChoice == 3) {
                           if (!HeroList.get(f).fireSpellsInventory.isEmpty()) {
                               System.out.println("Welcome to your Fire Spell Inventory . Your Spells are listed below :");
                               for (int i = 0; i < HeroList.get(f).fireSpellsInventory.size(); i++) {
                                   System.out.println("(" + (i + 1) + ")" + HeroList.get(f).fireSpellsInventory.get(i).Name);
                               }
                               int fireSpellChoice = UserInput("-->", HeroList.get(f).fireSpellsInventory.size());
                               sellfireSpellsfromInventory(HeroList.get(f),fireSpellChoice - 1);
                           } else {

                               System.out.println("Inventory Empty Today ! Check Another");
                               ShopFromMarket();
                           }

                       } else if (InventoryChoice == 4) {
                           if (!HeroList.get(f).iceSpellsInventory.isEmpty()) {
                               System.out.println("Welcome to your Ice Spell Inventory . Your Spells are listed below :");
                               for (int i = 0; i < HeroList.get(f).iceSpellsInventory.size(); i++) {
                                   System.out.println("(" + (i + 1) + ")" + HeroList.get(f).iceSpellsInventory.get(i).Name);
                               }
                               int iceSpellChoice = UserInput("-->", HeroList.get(f).iceSpellsInventory.size());
                               selliceSpellsfromInventory(HeroList.get(f),iceSpellChoice - 1);
                           } else {

                               System.out.println("Inventory Empty Today ! Check Another");
                               ShopFromMarket();
                           }

                       } else if (InventoryChoice == 5) {
                           if (!HeroList.get(f).armorInventory.isEmpty()) {
                               System.out.println("Welcome to your Armor Inventory . Your Armors are listed below :");
                               for (int i = 0; i < HeroList.get(f).armorInventory.size(); i++) {
                                   System.out.println("(" + (i + 1) + ")" + HeroList.get(f).armorInventory.get(i).Name);
                               }
                               int armorChoice = UserInput("-->", HeroList.get(f).armorInventory.size());
                               sellArmorfromInventory(HeroList.get(f),armorChoice - 1);
                           } else {

                               System.out.println("Inventory Empty Today ! Check Another");
                               ShopFromMarket();
                           }
                       } else if (InventoryChoice == 6) {
                           if (!HeroList.get(f).potionsInventory.isEmpty()) {
                               System.out.println("Welcome to your Potions Inventory . Your Potions are listed below :");
                               for (int i = 0; i < HeroList.get(f).potionsInventory.size(); i++) {
                                   System.out.println("(" + (i + 1) + ")" + HeroList.get(f).potionsInventory.get(i).Name);
                               }
                               int armorChoice = UserInput("-->", HeroList.get(f).potionsInventory.size());
                               sellPotionsfromInventory(HeroList.get(f),armorChoice - 1);
                           } else {

                               System.out.println("Inventory Empty Today ! Check Another");
                               ShopFromMarket();
                           }
                       }
                   }
               }
           }
       }
   }

    private static void sellPotionsfromInventory(Heroes player,int i) {
        if(!player.potionsInventory.isEmpty()){
            System.out.println("Do you really want to sell the Potion ?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            int sellChoice = UserInput("-->",2);
            if(sellChoice == 1){
                player.gold += player.potionsInventory.get(i).price/2;
                market.PotionsMarket.add(player.potionsInventory.get(i));
                player.potionsInventory.remove(i);
                System.out.println("Thanks for adding a Potion to the Inventory.");
            }
            if(sellChoice == 2){
                System.out.println("Thanks for Coming here.");
                ShopFromMarket();
            }
        }
        else{
            System.out.println("You don't have anything in your inventory.");
            ShopFromMarket();
        }
    }

    private static void BuyPotionsFromMarket(Heroes player,int i) {
        System.out.println("Do you really want to buy ?");
        System.out.println("(1) Yes");
        System.out.println("(2) No");
        int buyChoice = UserInput("-->",2);
        if(buyChoice == 1){
            if(player.gold>market.PotionsMarket.get(i).price){
                player.gold -= market.PotionsMarket.get(i).price;
                player.potionsInventory.add(market.PotionsMarket.get(i));
                market.PotionsMarket.remove(i);
                System.out.println("You have successfully bought "+market.PotionsMarket.get(i).Name);
            }
            else{
                System.out.println("You don't have enough money .");
                ShopFromMarket();
            }
        }
    }

    private static void sellLightningSpellsfromInventory(Heroes player,int i) {
        if(!player.lightningSpellsInventory.isEmpty()){
            System.out.println("Do you really want to sell the Lightning Spell ?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            int sellChoice = UserInput("-->",2);
            if(sellChoice == 1){
                player.gold += player.lightningSpellsInventory.get(i).price/2;
                market.LightningSpellsMarket.add(player.lightningSpellsInventory.get(i));
                player.lightningSpellsInventory.remove(i);
                System.out.println("Thanks for adding a Lightning Spell to the Inventory.");
            }
            if(sellChoice == 2){
                System.out.println("Thanks for Coming here.");
                ShopFromMarket();
            }
        }
        else{
            System.out.println("You don't have anything in your inventory.");
            ShopFromMarket();
        }
    }

    private static void sellfireSpellsfromInventory(Heroes player,int i) {
        if(!player.fireSpellsInventory.isEmpty()){
            System.out.println("Do you really want to sell the Fire Spell ?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            int sellChoice = UserInput("-->",2);
            if(sellChoice == 1){
                player.gold += player.fireSpellsInventory.get(i).price/2;
                market.FireSpellsMarket.add(player.fireSpellsInventory.get(i));
                player.fireSpellsInventory.remove(i);
                System.out.println("Thanks for adding a Fire Spell to the Inventory.");
            }
            if(sellChoice == 2){
                System.out.println("Thanks for Coming here.");
                ShopFromMarket();
            }
        }
        else{
            System.out.println("You don't have anything in your inventory.");
            ShopFromMarket();
        }

    }

    private static void selliceSpellsfromInventory(Heroes player ,int i) {
        if(!player.iceSpellsInventory.isEmpty()){
            System.out.println("Do you really want to sell the Ice Spell ?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            int sellChoice = UserInput("-->",2);
            if(sellChoice == 1){
                player.gold += player.iceSpellsInventory.get(i).price/2;
                market.IceSpellsMarket.add(player.iceSpellsInventory.get(i));
                player.iceSpellsInventory.remove(i);
                System.out.println("Thanks for adding a Ice Spell to the Inventory.");
            }
            if(sellChoice == 2){
                System.out.println("Thanks for Coming here.");
                ShopFromMarket();
            }
        }
        else{
            System.out.println("You don't have anything in your inventory.");
            ShopFromMarket();
        }

    }

    private static void sellArmorfromInventory(Heroes player,int i) {
        if(!player.armorInventory.isEmpty()){
            System.out.println("Do you really want to sell the Armor ?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            int sellChoice = UserInput("-->",2);
            if(sellChoice == 1){
                player.gold += player.armorInventory.get(i).price/2;
                market.ArmorMarket.add(player.armorInventory.get(i));
                player.armorInventory.remove(i);
                System.out.println("Thanks for adding a Armor to the Inventory.");
            }
            if(sellChoice == 2){
                System.out.println("Thanks for Coming here.");
                ShopFromMarket();
            }
        }
        else{
            System.out.println("You don't have anything in your inventory.");
            ShopFromMarket();
        }
    }

    private static void sellWeaponfromInventory(Heroes player,int i) {
        if(!player.weaponInventory.isEmpty()){
            System.out.println("Do you really want to sell the Weapon ?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            int sellChoice = UserInput("-->",2);
            if(sellChoice == 1){
                player.gold += player.weaponInventory.get(i).price/2;
                market.WeaponMarket.add(player.weaponInventory.get(i));
                player.weaponInventory.remove(i);
                System.out.println("Thanks for adding a weapon to the Inventory.");
            }
            if(sellChoice == 2){
                System.out.println("Thanks for Coming here.");
                ShopFromMarket();
            }
        }
        else{
            System.out.println("You don't have anything in your inventory.");
            ShopFromMarket();
        }
    }

    private static void BuyFireSpellFromMarket(Heroes player,int i) {
        System.out.println("Do you really want to buy ?");
        System.out.println("(1) Yes");
        System.out.println("(2) No");
        int buyChoice = UserInput("-->",2);
        if(buyChoice == 1){
            if(player.gold>market.FireSpellsMarket.get(i).price){
                player.gold -= market.FireSpellsMarket.get(i).price;
                player.fireSpellsInventory.add(market.FireSpellsMarket.get(i));
                market.FireSpellsMarket.remove(i);
                System.out.println("You have successfully bought "+market.FireSpellsMarket.get(i).Name);
            }
            else{
                System.out.println("You don't have enough money .");
                ShopFromMarket();
            }
        }
    }

    private static void BuyArmorFromMarket(int i) {
        System.out.println("Do you really want to buy ?");
        System.out.println("(1) Yes");
        System.out.println("(2) No");
        int buyChoice = UserInput("-->",2);
        if(buyChoice == 1){
            if(player.gold>market.ArmorMarket.get(i).price){
                player.gold -= market.ArmorMarket.get(i).price;
                player.armorInventory.add(market.ArmorMarket.get(i));
                market.ArmorMarket.remove(i);
                System.out.println("You have successfully bought "+market.ArmorMarket.get(i).Name);
            }
            else{
                System.out.println("You don't have enough money .");
                ShopFromMarket();
            }
        }
    }


    private static void BuyIceSpellFromMarket(Heroes player,int i) {
        System.out.println("Do you really want to buy ?");
        System.out.println("(1) Yes");
        System.out.println("(2) No");
        int buyChoice = UserInput("-->",2);
        if(buyChoice == 1){
            if(player.gold>market.IceSpellsMarket.get(i).price){
                player.gold -= market.IceSpellsMarket.get(i).price;
                player.iceSpellsInventory.add(market.IceSpellsMarket.get(i));
                market.IceSpellsMarket.remove(i);
                System.out.println("You have successfully bought "+market.IceSpellsMarket.get(i).Name);
            }
            else{
                System.out.println("You don't have enough money .");
                ShopFromMarket();
            }

        }

    }

    private static void BuyLightningSpellFromMarket(Heroes player,int i) {
        System.out.println("Do you really want to buy ?");
        System.out.println("(1) Yes");
        System.out.println("(2) No");
        int buyChoice = UserInput("-->",2);
        if(buyChoice == 1){
            if(player.gold>market.LightningSpellsMarket.get(i).price){
                player.gold -= market.LightningSpellsMarket.get(i).price;
                player.lightningSpellsInventory.add(market.LightningSpellsMarket.get(i));
                market.LightningSpellsMarket.remove(i);
                System.out.println("You have successfully bought "+market.LightningSpellsMarket.get(i).Name);
            }
            else{
                System.out.println("You don't have enough money .");
                ShopFromMarket();
            }
        }
    }

    private static void BuyWeaponFromMarket(Heroes player,int i) {
        System.out.println("Do you really want to buy ?");
        System.out.println("(1) Yes");
        System.out.println("(2) No");
        int buyChoice = UserInput("-->",2);
        if(buyChoice == 1){
            if(player.gold>market.WeaponMarket.get(i).price){
                player.gold -= market.WeaponMarket.get(i).price;
                player.weaponInventory.add(market.WeaponMarket.get(i));
                market.WeaponMarket.remove(i);
                for(int k = 0 ;k < player.weaponInventory.size(); k++) {
                    System.out.println("You have successfully bought " + player.weaponInventory.get(k).Name);
                }

            }
            else{
                System.out.println("You don't have enough money .");
                ShopFromMarket();
            }
        }
    }


    public static void shop (){

        printHeader("Shop / Market Arrived . Offers are as follows : ");

        printMarket();
        int price = (int)(Math.random()*(10 + player.pots*3)+ 10 +player.pots);
        System.out.println("Magic Potion : "+ price+" gold coins. ");
        printSeparation(20);
        System.out.println("Do you want to buy one : ");
        System.out.println("(1) Yes");
        System.out.println("(2) No , Thanks for the offer .");
        int newUserChoice = UserInput("-->",2);
        if(newUserChoice ==1){
            if(player.gold >= price){
                printHeader("You bought a magical potion for "+ price + " gold coins.");
                player.pots++;
                player.gold -= price;

            }
            else{
                printHeader("You don't have enough money to buy the potion");


            }
        }





    }
    public static void takeRest(){
       if(!HeroList.isEmpty()){
           for(int t = 0 ; t< HeroList.size() ; t ++){
        if(HeroList.get(t).restsLeft >= 1 ){
            printHeader("Do you want to take some rest ? ("+HeroList.get(t).restsLeft+" rest(s) left) . ");
            System.out.println("(1) Yes");
            System.out.println("(2) No, not right now.");
            int UserRestChoice = UserInput(" -- > ",2);
            if(UserRestChoice == 1){
                if(HeroList.get(t).HP < player.MaxHP){
                    int HealthRestored = (int) (Math.random()*(HeroList.get(t).XP/4 + 1)+ 10);
                    HeroList.get(t).HP += HealthRestored;
                    if(HeroList.get(t).HP > HeroList.get(t).MaxHP){
                        HeroList.get(t).HP = HeroList.get(t).MaxHP;
                    }
                    System.out.println("You took a rest and restored your health up to "+HealthRestored+ " Health.");
                    System.out.println("You are now at "+ HeroList.get(t).HP +"/"+HeroList.get(t).MaxHP+" Health.");
                    HeroList.get(t).restsLeft--;
                }

            }
            else{
                System.out.println("You are at full health , you don't need to rest anymore . ");
            }
        }
    }}}
    public static void finalWar(){
        EnemyList.add(new Villains("The Cruel Mercenary",2,10000,10000,500));
        Collision(randomVillain(EnemyList));
//        for(int i = 0 ; i < FaintedHeroList.size(); i++){
//            FaintedHeroList.get(i).HP = FaintedHeroList.get(i).MaxHP/2;
//            HeroList.add(FaintedHeroList.get(i));
//            FaintedHeroList.remove(i);
//            FaintedHeroList.get(i).HP = FaintedHeroList.get(i).MaxHP/2;
//
//        }
//        for(int j=0;j<HeroList.size();j++){
//            System.out.println("Heroes added "+HeroList.get(j).CharacterName);
//        }

//        CharacterStory.printEndOfStory(player);
            newBoard.userMove(size,'H');

//        isPacing = false;
    }
}
