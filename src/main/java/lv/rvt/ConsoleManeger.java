package lv.rvt;

public class ConsoleManeger {
    public static void title(){
        System.out.println(" /$$$$$$$$                            /$$       /$$                      ⠈⠛⠻⠶⣶⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("| $$_____/                           | $$      | $$                       ⠀⠀⠀⠈⢻⣆⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⠀⠀⠀");
        System.out.println("| $$     /$$$$$$   /$$$$$$   /$$$$$$$| $$$$$$$ | $$ /$$   /$$          ⠀⠀⠀⠀⠀⠀⠀⢻⡏⠉⠉⠉⠉⢹⡏⠉⠉⠉⠉⣿⠉⠉⠉⠉⠉⣹⠇⠀⠀⠀");
        System.out.println("| $$$$$ /$$__  $$ /$$__  $$ /$$_____/| $$__  $$| $$| $$  | $$          ⠀⠀⠀⠀⠀⠀⠀⠈⣿⣀⣀⣀⣀⣸⣧⣀⣀⣀⣀⣿⣄⣀⣀⣀⣠⡿⠀⠀⠀⠀");
        System.out.println("| $$__/| $$  \\__/| $$$$$$$$|  $$$$$$ | $$  \\ $$| $$| $$  | $$          ⠀⠀⠀⠀⠀⠀⠀⠸⣧⠀⠀⠀⢸⡇⠀⠀⠀⠀⣿⠁⠀⠀⠀⣿⠃⠀⠀⠀⠀");
        System.out.println("| $$   | $$      | $$_____/ \\____  $$| $$  | $$| $$| $$  | $$          ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣧⣤⣤⣼⣧⣤⣤⣤⣤⣿⣤⣤⣤⣼⡏⠀⠀⠀⠀⠀");
        System.out.println("| $$   | $$      |  $$$$$$$ /$$$$$$$/| $$  | $$| $$|  $$$$$$$                   ⢿⠀⠀⢸⡇⠀⠀⠀⠀⣿⠀⠀⢠⡿⠀⠀⠀⠀⠀⠀");
        System.out.println("|__/   |__/       \\_______/|_______/ |__/  |__/|__/ \\____  $$           ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣷⠤⠼⠷⠤⠤⠤⠤⠿⠦⠤⠾⠃⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                    /$$  | $$          ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                   |  $$$$$$/          ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢾⣷⢶⣶⠶⠶⠶⠶⠶⠶⣶⠶⣶⡶⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                                    \\______/             ⠀⠀⠀⠀⠀⠀⠀⠸⣧⣠⡿⠀⠀⠀⠀⠀⠀⢷⣄⣼⠇⠀⠀⠀⠀⠀⠀⠀");
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
}
