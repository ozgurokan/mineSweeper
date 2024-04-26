import java.util.Scanner;

public class Helper {

    public static final Scanner input = new Scanner(System.in);
    public static final String win_message = "Tebrikler Oyunu Kazandınız";
    public static final String lose_massage = "Maalesef Mayın Patladı ve Oyunu Kaybettiniz";
    public static final String invalid_selection_message = "Geçerli Bir Konum giriniz";
    public static final String greeting = "Mayın Tarlası Oyununa Hoşgeldiniz";
    public static final String step_message = "Basılacak konum:";
    public static final String already_choose = "Bu konumu daha önce zaten seçtin başka bir konum dene!";


    public static String playerName = null;
    public static int row = 0;
    public static int col = 0;



    public static void createPlayerName(){
        System.out.print("Hoşgeldiniz! Kullanıcı Adınızı Girin: ");
        playerName =  input.nextLine();
    }

    public static void createSize(){
        System.out.println("Oyun Tahtası boyutu giriniz(en az 2x2 olmalıdır): ");
        System.out.print("Sütun sayısı: ");
        int preColumn = input.nextInt();
        System.out.print("Satır Sayısı: ");
        int preRow = input.nextInt();

        if(preRow < 2 || preColumn < 2){
            System.out.println("Geçerli Sayı giriniz:");
        }else{
            row = preRow;
            col = preColumn;
        }
    }





}
