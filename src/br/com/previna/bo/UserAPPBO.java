package br.com.previna.bo;

import br.com.previna.dao.UserAPPDAO;
import br.com.previna.exception.ValidationException;
import br.com.previna.model.User;
import br.com.previna.model.UserAPP;
import br.com.previna.util.Constantes;
import br.com.previna.util.EncryptUtil;
import br.com.previna.util.MensagemContantes;
import br.com.previna.util.Validator;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserAPPBO {

    private UserAPPDAO userAPPDAO;

    public UserAPPBO() {
        userAPPDAO = new UserAPPDAO();
    }

    public void setUserAPPDAO(UserAPPDAO userAPPDAO) {
        this.userAPPDAO = userAPPDAO;
    }


    public boolean createUser(UserAPP user) throws Exception {
        try {
            if (user != null) {

                String encryptedPassword = EncryptUtil.encrypt2(user.getPassword());
                user.setPassword(encryptedPassword);
                userAPPDAO.save(user);
                return true;
            }
            throw new ValidationException("Usuário inválido");
        } catch(ValidationException e) {
            throw e;
        } catch(Exception e) {
            throw new Exception("Erro ao criar o usuário: " + e.getMessage());
        }
    }


    public long updateUser(UserAPP user) throws ValidationException {
        if (user != null) {
            return userAPPDAO.updateUser(user);
        }
        throw new ValidationException("invalido");

    }


//    public User userExists(User userLogin) throws NoSuchAlgorithmException, ValidationException {
//        userLogin.setPassword(EncryptUtil.encrypt2(userLogin.getPassword()));
//        User returnedUser = userAPPDAO.findUserByCPF(userLogin.getCpf());
//        if (!userLogin.getPassword().equals(returnedUser.getPassword()))
//            throw new ValidationException(MensagemContantes.MSG_ERR_USUARIO_SENHA_INVALIDOS);
//        return returnedUser;
//    }

    public HashMap<String, List<UserAPP>> listUser() {
        ArrayList<UserAPP> users = null;
        HashMap<String, List<UserAPP>> listUsers = new HashMap<String, List<UserAPP>>();
        users = (ArrayList<UserAPP>) userAPPDAO.listAll(UserAPP.class);
        listUsers.put("Users", users);
        return listUsers;
    }



    public UserAPP getUserById(long id) throws ValidationException {
        if (id > 0) {
            return userAPPDAO.findUserByID(id);
        }
        throw new ValidationException("invalido");

    }

    public UserAPP getUserByEmail(String email) throws ValidationException {
        if(email.length()>0)
            return userAPPDAO.findUserByEmail(email);
        throw new ValidationException("Email Invalido");

    }

//    public boolean recuperaSenha(User recuperando, String password) {
//        try {
//            String encryptedPassword = EncryptUtil.encrypt2(password);
//            recuperando.setPassword(encryptedPassword);
//            userAPPDAO.updateUser(recuperando);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return true;
//
//    }
}
