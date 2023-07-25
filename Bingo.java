import java.util.*;

public class Bingo {
    public static void main(String[] args) {
        
        System.out.println("WelCome BINGO game...");
        delay();
        Scanner sc=new Scanner(System.in);
        int userBoard[][]={
            {16,18,17,19,20},
            {10,9,8,7,6},
            {11,12,13,14,15},
            {1,2,3,4,5},
            {21,22,23,24,25}
         };
        /*for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                userBoard[i][j]=sc.nextInt();
            }
        }*/
       int cpuBoard[][]=new int[5][5];
        Random rand=new Random();
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                int n=rand.nextInt(25)+1;
                while(isThere(n,i,cpuBoard)){
                    n=rand.nextInt(25)+1;
                }
                cpuBoard[i][j]=n;
            }
        }
        display(cpuBoard);
        System.out.println();
        int countUser=0;
        int countCup=0;
        ArrayList <Integer> Xnums=new ArrayList<>();
        while(countUser<5 && countCup<5){
            
            display(userBoard);
            System.out.println("Enter number (1-25).");
            int n=sc.nextInt();
            while(Xnums.contains(n) || n>25){
                n=sc.nextInt();
            }
            replace(n,userBoard,cpuBoard);
            
            Xnums.add(n);
            delay();
            n=rand.nextInt(25)+1;
            while(Xnums.contains(n)){
                n=rand.nextInt(25)+1;
            }
            replace(n,userBoard,cpuBoard);
            Xnums.add(n);
            countCup=howMany(cpuBoard);
            countUser=howMany(userBoard);
            System.out.println(countCup+"  "+countUser);
            System.out.println();
        }
        if(countCup==countUser){
            System.out.println("Draw the Match...:|");
            delay();
            display(cpuBoard);
            System.out.println();
            delay();
            display(userBoard);
        }
        else if(countCup>countUser){
            System.out.println("CPU Won the Match... :(");
            delay();
            display(cpuBoard);
            System.out.println();
            delay();
            display(userBoard);
        }
        else if(countUser>countCup){
            System.out.println("You won the Match... :)");
            delay();
            display(cpuBoard);
            System.out.println();
            delay();
            display(userBoard);
        }
        
    }
    public static int howMany(int [][]board){
        int count=0;
        
        for(int i=0;i<5;i++){
            int n=0;
            for(int j=0;j<5;j++){
                if(board[i][j]==0){
                    n++;
                }
                else{
                    break;
                }
            }
            if(n>=5){
                count++;
            }
        }
        
        for(int i=0;i<5;i++){
            int n=0;
            for(int j=0;j<5;j++){
                if(board[j][i]==0){
                    n++;
                }
                else{
                    break;
                }
            }
            if(n>=5){
                count++;
            }
        }
        int a=0;
        int b=0;
        while(a<5 && b<5){
            if(board[a][b]!=0){
                break;
            }
            a++;
            b++;
        }
        if(a>=5 && b>=5){
            count++;
        }
        a=0;
        b=4;
        while(a<=5 && b>=0){
            if(board[a][b]!=0){
                break;
            }
           
            a++;
            b--;
        }
        if(a>=5 && b<=0){
            count++;
        }
        return count;
    }

    public static void replace(int n,int userBoard[][],int cpuBoard[][]){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(userBoard[i][j]==n){
                    userBoard[i][j]=0;
                }
                if(cpuBoard[i][j]==n){
                    cpuBoard[i][j]=0;
                }
            }
        }
    }

    public static boolean isThere(int n,int i,int [][]cpuBoard){
        boolean ans=false;
        for(int a=i;a>=0;a--){
            for(int b=4;b>=0;b--){
                if(cpuBoard[a][b]==n){
                   // ans=true;
                     return true;
                    //break;
                }
            }
        }
        return false;
    }
    public static void display(int board[][]){
        for(int row[]:board){
            for(int i=0;i<5;i++){
                if(row[i]>10){
                    System.out.print(row[i]+" ");
                }
                else{
                    System.out.print(row[i]+"  ");
                }
                
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void delay(){
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
