package by.it.prigozhanov.project.java.controller;

import by.it.prigozhanov.project.java.beans.Order;
import by.it.prigozhanov.project.java.beans.User;
import by.it.prigozhanov.project.java.controller.Action;
import by.it.prigozhanov.project.java.controller.Actions;
import by.it.prigozhanov.project.java.controller.Form;
import by.it.prigozhanov.project.java.controller.Utils;
import by.it.prigozhanov.project.java.dao.DAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class CommandProfile extends Action {
    public CommandProfile() {
    }

    public Action execute(HttpServletRequest request) {
        if(Form.isPost(request) && request.getParameter("logout") != null) {
            HttpSession user1 = request.getSession();
            user1.invalidate();
            return Actions.LOGIN.command;
        } else {
            User user = Utils.getSessionUser(request);

            if(user != null) {
                DAO dao = DAO.getInstance();
                try {
                    List<Order> orders = dao.order.getAll("WHERE ID="+user.getId());
                    request.setAttribute("orders", orders);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }
}
