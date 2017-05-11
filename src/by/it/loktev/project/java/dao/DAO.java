package by.it.loktev.project.java.dao;

public class DAO {

    static private DAO instance;

    private RoleDAO role;
    private UserDAO user;
    private TaskDAO ad;


    private DAO(){

    }

    static public DAO getInstance(){
        if ( instance==null ) {
            synchronized (DAO.class) {
                if (instance == null) {
                    instance = new DAO();
                    instance.user= UserDAO.getInstance();
                    instance.role=RoleDAO.getInstance();
                    instance.ad= TaskDAO.getInstance();
                }
            }
        }
        return instance;
    }

    public RoleDAO getRole() {
        return role;
    }

    public UserDAO getUser() {
        return user;
    }

    public TaskDAO getAd() {
        return ad;
    }
}
