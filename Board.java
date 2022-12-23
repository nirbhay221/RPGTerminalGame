import java.util.*;

public class Board {
    int row;
    int columns;
    Set<Integer> marketXelements;
    Set<Integer> marketYelements;
    Set<Integer> LandXelements;
    Set<Integer> LandYelements;
    ArrayList<Integer> marketX;
    ArrayList<Integer> marketY;
    ArrayList<Integer> LandX;
    ArrayList<Integer> LandY;
    ArrayList<Integer> NoManX;
    ArrayList<Integer> NoManY;
    boolean isMarket = false;
    boolean isLand = false;
    int HeroX;
    int HeroY;
    public char board[][] ;
    Scanner sc = new Scanner(System.in);
    public Board(int Rows,int Cols){
this.row = Rows;
this.columns = Cols;
board = new char[Rows][Cols];
marketXelements = new LinkedHashSet<Integer>();
marketYelements = new LinkedHashSet<Integer>();
        LandXelements = new LinkedHashSet<Integer>();
        LandYelements = new LinkedHashSet<Integer>();

        marketX = new ArrayList<Integer>(marketXelements);
        marketY = new ArrayList<Integer>(marketYelements);
        LandX = new ArrayList<Integer>(LandXelements);
        LandY = new ArrayList<Integer>(LandYelements);
        NoManX= new ArrayList<Integer>();
        NoManY = new ArrayList<Integer>();
    }
    public void initialize(int rows, int columnss){
     for(int i = 0; i < rows; i++) {
        for(int j = 0 ; j < columnss ; j++){

                board[i][j] =' ';


        }
    }
    }
    public void MarketcheckPosition(){
        for(int j =0 ; j< marketX.size() ;j++)
        {
//            System.out.println(marketX.(j)+","+marketY.get(j));

           if(HeroX == marketX.get(j)){
               if(HeroY == marketY.get(j)){
                   System.out.println("You are at the market .Do you want to buy something ?");
                   isMarket = true;
                   Logic.ShopFromMarket();
               }


        }}

    }
    public void LandcheckPosition(){
        for(int j =0 ; j< LandX.size() ;j++)
        {
//            System.out.println(marketX.(j)+","+marketY.get(j));
//            System.out.println(!isLand && isMarket);


            if(HeroX == LandX.get(j)){
                if(HeroY == LandY.get(j)){
//                    switch(Logic.act){
//                        case 1: CharacterStory.printFirstPlotIntro(); break;
//
//                    }

                    System.out.println("You are on the common Land .You might get in a fight with a monster .");
                    Logic.gameConditions();
                    isLand = true;

            }



        }}

    }
    public void AssignMarket(int size,char symbol){
        int var = (int) Math.floor( 0.3 * size);
        System.out.println("var size :"+var);
        int i = 0 ;

        for(i=0;i<var;i++){
            Random a = new Random();
            int x = a.nextInt((int) Math.sqrt(size));
            int y = a.nextInt((int) Math.sqrt(size));

            marketX.add(x);
            marketY.add(y);
            board[x][y] = symbol;

        }
        for(int j =0 ; j< marketX.size() ;j++)
        {
            System.out.println("Market is at positions on the land : "+marketX.get(j)+","+marketY.get(j));
        }


    }
    public void AssignNoManLand(int size,char symbol){
        int var = (int) Math.floor( 0.2 * size);
        System.out.println("var size :"+var);
        int i = 0 ;

        for(i=0;i<var;i++){
            Random a = new Random();
            int x = a.nextInt((int) Math.sqrt(size));
            int y = a.nextInt((int) Math.sqrt(size));

            NoManX.add(x);
            NoManY.add(y);
            board[x][y] = symbol;

        }
        for(int j =0 ; j< NoManX.size() ;j++)
        {
            System.out.println("No Man's Land is at positions on the land : "+NoManX.get(j)+","+NoManY.get(j));
        }


    }
    public void AssignLand(int size,char symbol){
        int var = (int) Math.floor( 0.5 * size);
        System.out.println("var size :"+var);
        int i = 0 ;
        marketX.add(4);
        marketY.add(4);
        for(i=0;i<var;i++){
            Random a = new Random();
            int x = a.nextInt((int) Math.sqrt(size))+0;
            int y = a.nextInt((int) Math.sqrt(size))+0;
            LandX.add(x);
            LandY.add(y);
            board[x][y] = symbol;

        }
        for(int j =0 ; j< LandX.size() ;j++)
        {
            System.out.println("Land is at positions on the land : "+LandX.get(j)+","+LandY.get(j));
        }


    }
    public void AddDefaultPos(int size,char symbol){
        Random rand = new Random();
        int x = rand.nextInt((int) Math.sqrt(size))+0;
        int y = rand.nextInt((int) Math.sqrt(size))+0;
        board[x][y] = symbol;
//        checkPosition();
        for (int i = 0; i < row; i++) {
            for(int j = 0 ; j < columns ; j++){
                if(board[i][j] == 'H'){

                    HeroX=i;
                    HeroY = j;
                }
            }}


    }
    public void userMove(int size,char symbol){
        printBoard();
//        MarketcheckPosition();
//        LandcheckPosition();

        System.out.println("Enter the place you want to move to : W : Up , A : Left , D : Right , S : Down , Q : Quit , I : Player Info , P: Print Board , K: Inventory Actions");
        String Input = sc.nextLine();
        while(!Input.equalsIgnoreCase("K") &&!Input.equalsIgnoreCase("Q") && !Input.equalsIgnoreCase("I")  && !Input.equalsIgnoreCase("P") && !Input.equalsIgnoreCase("W") && !Input.equalsIgnoreCase("A") && !Input.equalsIgnoreCase("S") && !Input.equalsIgnoreCase("D")){//if none of the above keywords are pressed ,
            // Invalid Message is called and user is asked to type the input again .
            System.out.println("Invalid input");
            Input = sc.nextLine();
        }

        if(Input.equalsIgnoreCase("Q")){
            System.out.println("Are you sure ?");
            System.out.println("Thanks for playing this , do try again for enjoying more of the adventure .");
            System.out.println("Your Game Ended. Sorry Try Again Next Time.");
            System.out.println("Do you want to Play Again ?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            int lastChoice = Logic.UserInput("-->",2);
            if(lastChoice == 1){
                Logic.StartGame();
            }
            else{
                System.out.println("Have a nice day ahead .");
                System.exit(0);
            }
        }
        if(Input.equalsIgnoreCase("I")){
            Logic.printallstats();
            userMove(size,symbol);
        }
        if(Input.equalsIgnoreCase("K")){
            Logic.inventorydisplay();
            userMove(size,symbol);
        }
        if(Input.equalsIgnoreCase("P")){
            printBoard();
            System.out.println("");
            System.out.println("");
            userMove(size,symbol);
        }

        if(Input.equalsIgnoreCase("W")){
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'H'){
                        if(i-1 < 0 ){
                            System.out.println("Start Again");
                            userMove(size,symbol);
                        } else if (board[i-1][j] == 'N') {
                            System.out.println("No Man's Land . Go Somewhere Else.");
                            userMove(size,symbol);
                        }
                        board[i][j] =' ';

                        board[--i][j] ='H';
                        System.out.println("x: "+ i+" && y : "+j);
                        HeroX=i;
                        HeroY = j;
                    }
                }
            }



            System.out.println(" ");
        }
        if(Input.equalsIgnoreCase("A")){
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'H'){
                        if(j-1 <0 ){
                            System.out.println("Start Again");
                            userMove(size,symbol);
                        }
                        else if (board[i][j-1] == 'N') {
                            System.out.println("No Man's Land . Go Somewhere Else.");
                            userMove(size,symbol);
                        }
                        board[i][j] =' ';
                        board[i][--j] ='H';
                        System.out.println("x: "+ i+" && y : "+j);
                        HeroX=i;
                        HeroY = j;
                    }
                }
            }



            System.out.println(" ");
        }

        if(Input.equalsIgnoreCase("S")){
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'H'){
                        if(i+1 == size ){
                            System.out.println("Start Again");
                            userMove(size,symbol);
                        }else if (board[i+1][j] == 'N') {
                            System.out.println("No Man's Land . Go Somewhere Else.");
                            userMove(size,symbol);
                        }
                        board[i][j] =' ';
                        board[++i][j] ='H';
                        System.out.println("x: "+ i+" && y : "+j);
                        HeroX=i;
                        HeroY = j;
                    }
                }
            }



            System.out.println(" ");
        }



        if(Input.equalsIgnoreCase("D")){
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'H'){
                        if(j+1 == size ){
                            System.out.println("Start Again");
                            userMove(size,symbol);
                        }
                        else if (board[i][j+1] == 'N') {
                            System.out.println("No Man's Land . Go Somewhere Else.");
                            userMove(size,symbol);
                        }
                        board[i][j] =' ';
                        board[i][++j] ='H';
                        System.out.println("x: "+ i+" && y : "+j);
                        HeroX=i;
                        HeroY = j;
                    }
                }
            }



            System.out.println(" ");
        }

//        checkPosition();
        MarketcheckPosition();
        LandcheckPosition();
        printBoard();
        System.out.println("  ");
        userMove(size,symbol);
    }
    public void UserChoiceMove(int wholeRow , int wholeColumn,char symbol){
        int newrow = 0 , column = 0 ;
        while(true){

        System.out.println("Enter the place you want to place on the board ");
        System.out.println("Enter the row ");
        newrow = sc.nextInt();
        System.out.println("Enter the column ");
        column = sc.nextInt();
        if(newrow< 0 || column < 0 || newrow> wholeRow || column > wholeColumn)
        {
            System.out.println("Rows or Columns are out of bounds ");
        }
//        else if(board[newrow][column] != 'M'){
//            System.out.println("Someone is on that tile ");
//
//        }
        else  {
            break;
        }


    }
        board[newrow][column] = symbol;
        printBoard();

    }
public void printBoard() {

//    for (int i = 0; i < Rows; i++) {
//        System.out.print("----------");
//    }
//
//    for (int i = 0; i < row; i++) {
//        for (int j= 0; j< columns; j++) {
//           board[i][j] = '_';
//        }
//    }

    for (int i = 0; i < row; i++) {

        System.out.print("| ");
        for(int j = 0 ; j < columns ; j++){
//            board[marketX.get(i)][marketY.get(i)]='M';
//            board[LandX.get(i)][LandY.get(i)]='L';
            System.out.print(board[i][j]);
            System.out.print( " | ");


        }
     System.out.println("");
//        for (int k = 0; k < Rows; k++) {
//            System.out.print("----------");
    }

}}
