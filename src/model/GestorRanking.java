package model;

import java.util.LinkedList;

public class GestorRanking {
    private LinkedList<User> userList;

    public GestorRanking(LinkedList<User> userList) {
        this.userList = userList;
    }

    public LinkedList<User> getUserList() {
        return userList;
    }

    public void setUserList(LinkedList<User> userList) {
        this.userList = userList;
    }

    public Ranking ordenaRanking() {
        Ranking ranking = new Ranking();
        intercambio(ranking.getUsers2x(), 1);
        llenaRanking(ranking.getUsers2x());
        intercambio(ranking.getUsers4x(), 2);
        llenaRanking(ranking.getUsers4x());
        intercambio(ranking.getUsersTournament(), 3);

        return ranking;
    }

    private void llenaRanking(LinkedList<User> users) {
        if(users.size()<=10){
            for(int i = users.size();i< 10; i++){
                User user= new User();
                user.setNickname("--");
                System.out.println(user.getNickname()+"");
                users.add(user);

            }

        }
    }


    public void intercambio(LinkedList<User> usuarisRanking, int mode) {

        //Usamos un bucle anidado
        User variableauxiliar;
      /*  int n = usuarisRanking.size();
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (recount(userList.get(i), mode) > recount(userList.get(k), mode)) {
                    //swapNumbers(i, k, array);
                    variableauxiliar = userList.get(i);
                    userList.set(i, userList.get(k));
                    userList.set(k, variableauxiliar);

                }
            }
            */
        for (int i = 0; i <userList.size() -1;i++) {
            for (int j = i; j < userList.size(); j++) {
                if (recount(userList.get(i), mode) < recount(userList.get(j), mode)) {
                    //Intercambiamos valores
                    variableauxiliar = userList.get(i);
                    userList.set(i, userList.get(j));
                    userList.set(j, variableauxiliar);
                }
            }
        }
        for(int i = 0; i< userList.size() && i<10;i++){
            System.out.println(userList.get(i).getNickname());
            usuarisRanking.add(userList.get(i));
        }
        }

    private long recount(User user , int mode) {
        return user.recountType(mode);
    }


}




