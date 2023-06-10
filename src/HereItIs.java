import java.util.*;

class User{
    String id;
    String password;
    String authority;
    String name;
    String phone_number;
    String email;

    public User(String id, String password, String authority, String name, String phone_number, String email){
        this.id=id; this.password=password; this.authority=authority; this.name=name; this.phone_number=phone_number; this.email=email;
    }
    public void setID(String id) { this.id=id; }
    public void setPW(String password) { this.password=password; }
    public void setAuthority(String authority) { this.authority=authority; }
    public void setName(String name) { this.name=name; }
    public void setPN(String phone_number) { this.phone_number=phone_number; }
    public void setEmail(String email) { this.email=email; }
    public String getID() { return id; }
    public String getPW() { return password; }
    public String getAuthority() { return authority; }
    public String getName() { return name; }
    public String getPN(){ return phone_number; }
    public String getEmail() { return email; }
    public static void showIDs(HashMap<String,User> userTable){
        LinkedList<String> userTableKeys=new LinkedList<>(userTable.keySet());
        for (String userTableKey : userTableKeys) {
            System.out.println(userTableKey);
        }
    }
}
class Stock{
    String name;
    String number;
    String amount;
    String date;

    public Stock(String name, String number, String amount, String date){
        this.name=name; this.number=number; this.amount=amount; this.date=date;
    }
    public void setName(String name) { this.name=name; }
    public void setNumber(String number) { this.number=number; }
    public void setAmount(String amount) { this.amount=amount; }
    public void setDate(String date) { this.date=date; }
    public String getName() { return name; }
    public String getNumber() { return number; }
    public String getAmount() { return amount; }
    public String getDate() { return date; }
    public static void showStocks(Stock stock){
        System.out.println(String.format("%10s%10s%6s%12s","상품 이름","일련번호","수량","입고일"));
        System.out.println(String.format("%10s%10s%6s%12s"
                ,stock.getName(),stock.getNumber(),stock.getAmount(),stock.getDate()));
    }
}
class Request{
    String name;
    String amount;

    public Request(String name, String amount){
        this.name=name; this.amount=amount;
    }
    public void setName(String name) { this.name=name; }
    public void setAmount(String amount) { this.amount=amount; }
    public String getName() { return name; }

    public String getAmount() { return amount; }
}
class Reservation{
    String name;
    String number;
    String amount;

    public Reservation(String name, String number, String amount){
        this.name=name; this.number=number; this.amount=amount;
    }
    public void setName(String name) { this.name=name; }
    public void setNumber(String number) { this.number=number; }
    public void setAmount(String amount) { this.amount=amount; }
    public String getName() { return name; }
    public String getNumber() { return  number; }
    public String getAmount() { return amount; }
    public static void showReservations(Reservation reserve){
        System.out.println("예약 상품 : "+reserve.getName()+"("+reserve.getNumber()+") "+reserve.getAmount()+"개");
    }
}
class StartMenu{
    static Scanner scanner=new Scanner(System.in);
    public static String init(){
        System.out.println("==============================");
        System.out.println("[여기 있어요?] Main Screen");
        System.out.println("==============================");
        System.out.println("1. 회원가입 | 2. 로그인");
        System.out.print("입력 : "); String menu=scanner.nextLine();
        return menu;
    }
    public static void join(HashMap<String,User> userTable){
        System.out.println("\n<< 회원가입 >>");
        System.out.println("==============================");
        System.out.print("ID : "); String id= scanner.nextLine();
        if(userTable.containsKey(id)){
            System.out.println("이미 존재하는 아이디입니다. 다시 진행해주세요.");
        }
        else{
            System.out.print("Password : "); String password= scanner.nextLine();
            System.out.print("Authority(customer/storeManager) : "); String authority= scanner.nextLine();
            if(!authority.equals("customer")&&!authority.equals("storeManager")){
                System.out.println("입력하신 권한은 부여할 수 없는 권한입니다. 다시 진행해주세요.");
            }
            else{
                System.out.print("Name : "); String name= scanner.nextLine();
                System.out.print("PhoneNumber : "); String phone_number= scanner.nextLine();
                System.out.print("Email : "); String email= scanner.nextLine();

                String idReg="^[a-zA-Z0-9]{4,12}$"; boolean valID=id.matches(idReg);
                String pwReg="^[a-zA-Z0-9]{4,12}$"; boolean valPW=password.matches(pwReg);
                String nameReg="^[가-힣]{2,6}$"; boolean valName=name.matches(nameReg);
                String phoneReg="^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$"; boolean valPhone=phone_number.matches(phoneReg);
                String emailReg="^[a-zA-Z0-9]+@[0-9a-zA-Z]+\\.[a-zA-Z]{2,6}$"; boolean valEmail=email.matches(emailReg);

                if(valID&&valPW&&valName&&valPhone&&valEmail){
                    User user= new User(id,password,authority,name,phone_number,email);
                    userTable.put(id,user);
                    System.out.println(name+"님 환영합니다.");
                }
                else{
                    System.out.println("양식에 맞지 않는 입력값이 존재합니다. 다시 진행해주세요.");
                }
            }
        }
        System.out.println("==============================");
    }
    public static User login(HashMap<String,User> userTable){
        System.out.println("\n<< 로그인 >>");
        System.out.println("==============================");
        System.out.print("ID : "); String id= scanner.nextLine();
        if(userTable.containsKey(id)){
            System.out.print("Password : "); String password= scanner.nextLine();
            if(userTable.get(id).getPW().equals(password)){
                System.out.println(id+"님 로그인 성공!");
                System.out.println("==============================");
                return userTable.get(id);
            }
            else{
                System.out.println("비밀번호가 틀렸습니다. 다시 진행해주세요.");
                System.out.println("==============================");
                return null;
            }
        }
        else{
            System.out.println("일치하는 회원 정보가 없습니다.");
            System.out.println("==============================");
            return null;
        }
    }
}
class CustomerMenu{
    static Scanner scanner=new Scanner(System.in);
    public static String init(){
        System.out.println("==============================");
        System.out.println("[여기 있어요?] Customer Menu");
        System.out.println("==============================");
        System.out.println("1. 재고확인");
        System.out.println("2. 상품예약 | 3. 예약취소 | 4. 예약확인");
        System.out.println("5. 상품 재입고 요청 | 6. 로그아웃");
        System.out.print("입력 : "); String menu=scanner.nextLine();
        return menu;
    }
    public static void stockChecking(HashMap<String,Stock> stockTable){
        System.out.println("\n<< 재고확인 >>");
        System.out.println("==============================");
        if(stockTable.isEmpty()){
            System.out.println("등록된 재고가 없습니다.");
        }
        else{
            System.out.println(String.format("%10s%10s%6s%12s","상품 이름","일련번호","수량","입고일"));
            LinkedList<Map.Entry<String,Stock>> list=new LinkedList<>(stockTable.entrySet());
            for(Map.Entry<String,Stock> me:list){
                System.out.println(String.format("%10s%10s%6s%12s"
                        ,me.getValue().getName()
                        ,me.getValue().getNumber()
                        ,me.getValue().getAmount()
                        ,me.getValue().getDate()));
            }
        }
        System.out.println("==============================");
    }
    public static void reserveGoods(HashMap<String,Stock> stockTable, HashMap<String,Reservation> reservationTable){
        System.out.println("\n<< 상품예약 >>");
        System.out.println("==============================");
        System.out.print("상품 이름 : "); String name= scanner.nextLine();
        System.out.print("일련번호 : "); String number=scanner.nextLine();
        System.out.print("상품 수량 : "); String amount= scanner.nextLine();
        if(reservationTable.containsKey(number)){
            if(Integer.parseInt(amount)<1){
                System.out.println("잘못된 입력값입니다. 다시 시도해주세요");
            }
            else{
                if(Integer.parseInt(amount)>Integer.parseInt(stockTable.get(number).getAmount())||!stockTable.containsKey(number)){
                    System.out.println("재고 수량이 부족합니다.");
                }
                else{
                    Reservation newReservation=new Reservation(name,number,amount);
                    reservationTable.put(newReservation.getNumber(),newReservation);
                    System.out.println("상품 예약이 완료되었습니다.");
                }
            }
        }
        else{
            System.out.println("존재하지 않는 상품입니다. 다시 시도해주세요");
        }
        System.out.println("==============================");
    }
    public static void cancelReservation(HashMap<String,Reservation> reservationTable){
        System.out.println("\n<< 예약취소 >>");
        System.out.println("==============================");
        if(reservationTable.isEmpty()){
            System.out.println("예약한 상품이 없어 취소가 불가합니다. 다시 시도해주세요.");
        }
        else{
            LinkedList<Map.Entry<String,Reservation>> list=new LinkedList<>(reservationTable.entrySet());
            for(Map.Entry<String,Reservation> me:list){
                Reservation.showReservations(me.getValue());
            }
            System.out.print("취소할 상품의 번호 입력 : "); String number= scanner.nextLine();
            if(reservationTable.containsKey(number)){
                reservationTable.remove(number);
                System.out.println("취소가 완료되었습니다.");
            }
            else{
                System.out.println("예약하지 않은 상품의 번호입니다. 다시 시도해주세요");
            }
        }
        System.out.println("==============================");
    }
    public static void confirmReservation(HashMap<String,Reservation> reservationTable){
        System.out.println("\n<< 예약확인 >>");
        System.out.println("==============================");
        LinkedList<Map.Entry<String,Reservation>> list=new LinkedList<>(reservationTable.entrySet());
        for(Map.Entry<String,Reservation> me:list){
            Reservation.showReservations(me.getValue());
        }
        System.out.println("==============================");
    }
    public static void requestRestock(HashMap<String, Request> requestTable){
        System.out.println("\n<< 상품 재입고 요청 >>");
        System.out.println("==============================");
        System.out.print("요청 상품 이름 : "); String name=scanner.nextLine();
        System.out.print("상품 수량 : "); String amount=scanner.nextLine();

        String rNameReg="^[가-힣]+$"; boolean valrName=name.matches(rNameReg);
        String rAmountReg="^[0-9]+$"; boolean valrAmount=amount.matches(rAmountReg);

        if(valrName&&valrAmount){
            if(requestTable.containsKey(name)){
                String currentAmount=requestTable.get(name).getAmount();
                String newAmount=Integer.toBinaryString(Integer.valueOf(currentAmount)+Integer.valueOf(amount));
                requestTable.get(name).setAmount(newAmount);
            }
            else{
                Request newRequest=new Request(name,amount);
                requestTable.put(newRequest.getName(), newRequest);
            }
            System.out.println("상품 요청이 완료되었습니다.");
        }
        else{
            System.out.println("양식에 맞지 않는 입력값이 존재합니다. 다시 진행해주세요.");
        }

        System.out.println("==============================");
    }
}
class StoreManagerMenu{
    static Scanner scanner=new Scanner(System.in);
    public static String init(){
        System.out.println("==============================");
        System.out.println("[여기 있어요?] Store Manager Menu");
        System.out.println("==============================");
        System.out.println("1. 재고확인 | 2. 재고 수량 반영");
        System.out.println("3. 상품 등록 및 삭제 | 4. 재입고 요청 확인");
        System.out.println("5. 로그아웃");
        System.out.print("입력 : "); String menu=scanner.nextLine();
        return menu;
    }
    public static void stockChecking(HashMap<String,Stock> stockTable){
        System.out.println("\n<< 재고확인 >>");
        System.out.println("==============================");
        if(stockTable.isEmpty()){
            System.out.println("등록된 재고가 없습니다.");
        }
        else{
            System.out.println(String.format("%10s%10s%6s%12s","상품 이름","일련번호","수량","입고일"));
            LinkedList<Map.Entry<String,Stock>> list=new LinkedList<>(stockTable.entrySet());
            for(Map.Entry<String,Stock> me:list){
                System.out.println(String.format("%10s%10s%6s%12s"
                        ,me.getValue().getName()
                        ,me.getValue().getNumber()
                        ,me.getValue().getAmount()
                        ,me.getValue().getDate()));
            }
        }
        System.out.println("==============================");
    }
    public static void reflectStock(HashMap<String,Stock> stockTable){
        System.out.println("\n<< 재고 수량 반영 >>");
        System.out.println("==============================");
        System.out.print("일련번호 : "); String number=scanner.nextLine();
        if(!stockTable.containsKey(number)){
            System.out.println("등록되지 않은 상품입니다. 다시 시도해주세요.");
        }
        else{
            Stock.showStocks(stockTable.get(number));
            System.out.println("\n1. 입고 | 2. 출고");
            System.out.print("입력 : "); int menu=scanner.nextInt(); scanner.nextLine();
            String newAmount="";
            switch(menu){
                case 1:{
                    System.out.println("\n[재고 입고]");
                    System.out.print("입고 수량 : "); String amount=scanner.nextLine();
                    newAmount=Integer.toString(Integer.valueOf(stockTable.get(number).getAmount())+Integer.valueOf(amount));
                    System.out.println("재고 입고에 성공하였습니다.");
                    break;
                }
                case 2:{
                    System.out.println("\n[재고 출고]");
                    System.out.println("출고 수량 : "); String amount=scanner.nextLine();
                    if(Integer.valueOf(stockTable.get(number).getAmount())>Integer.valueOf(amount)){
                        newAmount=Integer.toString(Integer.valueOf(stockTable.get(number).getAmount())-Integer.valueOf(amount));
                        System.out.println("재고 출고에 성공하였습니다.");
                    }
                    else{
                        System.out.println("현재 재고 수량보다 많은 값은 입력하셨습니다.");
                    }
                    break;
                }
                default:{
                    newAmount=stockTable.get(number).getNumber();
                    break;
                }
            }
            stockTable.get(number).setAmount(newAmount);
            Stock.showStocks(stockTable.get(number));
        }
        System.out.println("==============================");
    }
    public static void addDeleteGoods(HashMap<String,Stock> stockTable){
        System.out.println("\n<< 상품 등록 및 삭제 >>");
        System.out.println("==============================");
        System.out.println("1. 등록 | 2. 삭제");
        System.out.print("입력 : "); int menu=scanner.nextInt(); scanner.nextLine();
        switch (menu){
            case 1:{
                System.out.println("\n[상품 등록]");
                System.out.print("상품 이름 : "); String name=scanner.nextLine();
                System.out.print("일련번호 : "); String number= scanner.nextLine();
                if(stockTable.containsKey(number)){
                    System.out.println("이미 등록되어 있는 상품의 일련번호입니다.");
                    break;
                }
                System.out.print("수량 : "); String amount=scanner.nextLine();
                System.out.print("입고일 : "); String date=scanner.nextLine();

                String sNameReg="^[가-힣]+$"; boolean valsName=name.matches(sNameReg);
                String sNumberReg="^[0-9]{4}$"; boolean valsNumber=number.matches(sNumberReg);
                String sAmountReg="^[0-9]+$"; boolean valsAmount=amount.matches(sAmountReg);
                String sDateReg="^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$"; boolean valsDate=date.matches(sDateReg);

                if(valsName&&valsNumber&&valsAmount&&valsDate){
                    Stock newStock=new Stock(name,number,amount,date);
                    stockTable.put(newStock.getNumber(),newStock);
                    System.out.println("상품 등록에 성공하였습니다.");
                }
                else{
                    System.out.println("양식에 맞지 않는 입력값이 존재합니다. 다시 진행해주세요.");
                }
                break;
            }
            case 2:{
                System.out.println("\n[상품 삭제]");
                System.out.println("일련번호 : "); String number= scanner.nextLine();
                if(stockTable.containsKey(number)){
                    stockTable.remove(number);
                    System.out.println("상품 삭제에 성공하였습니다.");
                }
                else{
                    System.out.println("등록되어 있지 않은 상품입니다.");
                }
                break;
            }
            default:{
                System.out.println("지원하지 않는 기능입니다. 다시 시도해주세요.");
            }
        }
        System.out.println("==============================");
    }
    public static void confirmRequest(HashMap<String,Request> requestTable){
        System.out.println("\n<< 재입고 요청 확인 >>");
        System.out.println("==============================");
        if(requestTable.isEmpty()){
            System.out.println("재입고 요청이 없습니다.");
        }
        else{
            System.out.println(String.format("%10s%6s","상품 이름","수량"));
            LinkedList<Map.Entry<String,Request>> list=new LinkedList<>(requestTable.entrySet());
            for(Map.Entry<String,Request> me:list){
                System.out.println(String.format("%10s%6s"
                        ,me.getValue().getName()
                        , me.getValue().getAmount()));
            }
        }
        System.out.println("==============================");
    }
}
class AdministratorMenu {
    static Scanner scanner = new Scanner(System.in);
    public static String init() {
        System.out.println("==============================");
        System.out.println("[여기 있어요?] Administrator Menu");
        System.out.println("==============================");
        System.out.println("1. 회원관리 | 2. 로그아웃");
        System.out.print("입력 : "); String menu=scanner.nextLine();
        return menu;
    }
    public static void userManagement(HashMap<String,User> userTable){
        System.out.println("\n<< 회원 관리 >>");
        System.out.println("==============================");
        System.out.println("1. 회원 정보 수정 | 2. 회원 삭제");
        System.out.print("입력 : "); int menu1=scanner.nextInt(); scanner.nextLine();
        switch(menu1){
            case 1: {
                System.out.println("-------- 회원 정보 수정 --------");
                System.out.println("[회원 아이디]");
                User.showIDs(userTable);
                System.out.print("수정할 회원의 아이디를 입력하시오 >> ");
                String ID=scanner.nextLine();
                if(!userTable.containsKey(ID)){
                    System.out.println("입력하신 ID는 존재하지 않는 ID입니다. 다시 진행해주세요");
                }
                User user=userTable.get(ID);
                System.out.println("1. 아이디 | 2. 비밀번호 | 3. 권한 ");
                System.out.println("4. 이름 | 5. 핸드폰번호 | 6. 이메일");
                System.out.print("입력 : "); int menu2= scanner.nextInt(); scanner.nextLine();
                switch(menu2){
                    case 1:{
                        System.out.print("변경할 아이디 : ");
                        String id=scanner.next();
                        if(!userTable.containsKey(id)) {
                            user.setID(id);
                            System.out.println("수정이 완료되었습니다.");
                        }
                        else{
                            System.out.println("이미 존재하는 아이디 입니다. 다시 시도해주세요.");
                        }
                        break;
                    }
                    case 2:{
                        System.out.print("변경할 비밀번호 : ");
                        String password=scanner.next();
                        user.setPW(password);
                        System.out.println("수정이 완료되었습니다.");
                        break;
                    }
                    case 3:{
                        System.out.print("변경할 권한 : ");
                        String authority=scanner.next();
                        user.setAuthority(authority);
                        System.out.println("수정이 완료되었습니다.");
                        break;
                    }
                    case 4:{
                        System.out.print("변경할 이름 : ");
                        String name=scanner.next();
                        user.setName(name);
                        System.out.println("수정이 완료되었습니다.");
                        break;
                    }
                    case 5:{
                        System.out.print("변경할 핸드폰 번호 : ");
                        String phone_nubmer=scanner.next();
                        user.setPN(phone_nubmer);
                        System.out.println("수정이 완료되었습니다.");
                        break;
                    }
                    case 6:{
                        System.out.print("변경할 이메일 : ");
                        String email=scanner.next();
                        user.setEmail(email);
                        System.out.println("수정이 완료되었습니다.");
                        break;
                    }
                    default:{
                        System.out.println("지원하지 않는 기능입니다. 다시 시도해주세요.");
                    }
                }
                break;
            }
            case 2:{
                System.out.println("----------[회원 정보 삭제]----------");
                System.out.println("[회원 아이디]");
                User.showIDs(userTable);
                System.out.print("삭제할 회원의 아이디를 입력하시오 >> ");
                String ID=scanner.nextLine();
                userTable.remove(ID);
                System.out.println("삭제가 완료되었습니다.");
                break;
            }
        }
        System.out.println("==============================");
    }
}
public class HereItIs {
    public static void main(String[] args) {
        HashMap<String,User> userTable=new HashMap<String,User>();
        HashMap<String,Stock> stockTable=new HashMap<String,Stock>();
        HashMap<String,Request> requestTable= new HashMap<String, Request>();
        HashMap<String,Reservation> reservationTable=new HashMap<String,Reservation>();

        User administrator=new User("MainAdm","0000","administrator","관리자","010-1234-5678","adm@ynu.ac.kr");
        userTable.put(administrator.getID(),administrator);

        while(true){
            String startMenu=StartMenu.init();
            switch(startMenu){
                case "1": StartMenu.join(userTable); break;
                case "2": {
                    User user = StartMenu.login(userTable);
                    if(user==null) break;
                    while (true) {
                        if (user.getAuthority().equals("customer")) {
                            String customerMenu = CustomerMenu.init();
                            switch (customerMenu) {
                                case "1": {CustomerMenu.stockChecking(stockTable); break;}
                                case "2": {CustomerMenu.reserveGoods(stockTable,reservationTable); break;}
                                case "3": {CustomerMenu.cancelReservation(reservationTable); break;}
                                case "4": {CustomerMenu.confirmReservation(reservationTable); break;}
                                case "5": {CustomerMenu.requestRestock(requestTable); break;}
                                case "6": {user.setAuthority("c"); break;}
                                default: {
                                    System.out.println("지원하지 않는 기능입니다. 다시 시도해주세요."); break;
                                }
                            }
                        }
                        else if(user.getAuthority().equals("storeManager")){
                            String storeManagerMenu=StoreManagerMenu.init();
                            switch(storeManagerMenu){
                                case "1": {StoreManagerMenu.stockChecking(stockTable); break;}
                                case "2": {StoreManagerMenu.reflectStock(stockTable); break;}
                                case "3": {StoreManagerMenu.addDeleteGoods(stockTable); break;}
                                case "4": {StoreManagerMenu.confirmRequest(requestTable); break;}
                                case "5": {user.setAuthority("s"); break;}
                                default: {
                                    System.out.println("지원하지 않는 기능입니다. 다시 시도해주세요."); break;
                                }
                            }
                        }
                        else if(user.getAuthority().equals("administrator")){
                            String administratorMenu=AdministratorMenu.init();
                            switch(administratorMenu){
                                case "1": {AdministratorMenu.userManagement(userTable); break;}
                                case "2": {user.setAuthority("a"); break;}
                                default: {
                                    System.out.println("지원하지 않는 기능입니다. 다시 시도해주세요."); break;
                                }
                            }

                        }
                        else{
                            System.out.println("권한이 없어 로그아웃 되었습니다.");
                            if(user.getAuthority().equals("c")) user.setAuthority("customer");
                            else if(user.getAuthority().equals("s")) user.setAuthority("storeManager");
                            else if(user.getAuthority().equals("a")) user.setAuthority("administrator");
                            break;
                        }
                    }
                    break;
                }
                default: {
                    System.out.println("지원하지 않는 기능입니다. 다시 시도해주세요."); break;
                }
            }
        }
    }
}
