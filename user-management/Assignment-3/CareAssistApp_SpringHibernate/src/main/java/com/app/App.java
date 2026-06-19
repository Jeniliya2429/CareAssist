package com.app;

import com.app.config.AppConfig;
import com.app.dao.AuthDao;
import com.app.dao.ClaimDao;
import com.app.enums.ClaimStatus;
import com.app.exception.InvalidOwnershipException;
import com.app.exception.ResourceNotFoundException;
import com.app.model.Claim;
import com.app.model.User;
import jakarta.persistence.NoResultException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        AuthDao authDao = context.getBean(AuthDao.class);

        ClaimDao claimDao = context.getBean(ClaimDao.class);

        Scanner sc = new Scanner(System.in);

        System.out.println("------CAREASSIST LOGIN------");

        System.out.println("Enter Email");
        String email = sc.next();

        System.out.println("Enter Password");
        String password = sc.next();

        try {

            User user = authDao.login(email, password);

            switch(user.getRole().toString()){

                case "PATIENT":

                    while(true){

                        System.out.println("\n===== CLAIM MENU =====");

                        System.out.println("1. Add Claim");
                        System.out.println("2. Delete Claim");
                        System.out.println("3. Fetch All Claims");
                        System.out.println("4. Update Claim");
                        System.out.println("5. Fetch Claim By Id");
                        System.out.println("0. Exit");

                        int op = sc.nextInt();

                        if(op == 0)
                            break;

                        switch(op){

                            case 1:

                                System.out.println("Enter Claim Amount");

                                double amount = sc.nextDouble();

                                claimDao.save(new Claim(amount), email);

                                System.out.println("Claim Added");

                                break;

                            case 2:

                                System.out.println("Enter Claim Id");

                                int id = sc.nextInt();

                                try{

                                    claimDao.deleteById(id, email);

                                    System.out.println("Claim Deleted");

                                }
                                catch(ResourceNotFoundException | InvalidOwnershipException e){

                                    System.out.println(e.getMessage());
                                }

                                break;

                            case 3:

                                claimDao.findAll(email).forEach(System.out::println);

                                break;

                            case 4:

                                System.out.println("Enter Claim Id");

                                id = sc.nextInt();

                                try{

                                    Claim claim = claimDao.getById(id, email);

                                    System.out.println("Existing Claim");

                                    System.out.println(claim);

                                    System.out.println("Enter New Amount");

                                    claim.setAmount(sc.nextDouble());

                                    System.out.println("Enter New Status");

                                    claim.setStatus(ClaimStatus.valueOf(sc.next().toUpperCase()));

                                    claimDao.update(claim);

                                    System.out.println("Claim Updated");

                                }
                                catch(ResourceNotFoundException | InvalidOwnershipException e){

                                    System.out.println(e.getMessage());
                                }

                                break;

                            case 5:

                                System.out.println("Enter Claim Id");

                                id = sc.nextInt();

                                try{

                                    Claim claim = claimDao.getById(id, email);

                                    System.out.println(claim);

                                }
                                catch(ResourceNotFoundException | InvalidOwnershipException e){

                                    System.out.println(e.getMessage());
                                }

                                break;
                        }
                    }

                    break;
                case "ADMIN":

                    while(true){

                        System.out.println("1. View All Claims");
                        System.out.println("2. Approve Claim");
                        System.out.println("3. Reject Claim");
                        System.out.println("0. Exit");

                        int op = sc.nextInt();

                        if(op == 0)
                            break;

                        switch(op){

                            case 1:

                                claimDao.findAllClaims().forEach(System.out::println);

                                break;

                            case 2:

                                System.out.println("Enter Claim Id");

                                int claimId = sc.nextInt();

                                claimDao.updateStatus(claimId, ClaimStatus.APPROVED);

                                System.out.println("Claim Approved");

                                break;

                            case 3:

                                System.out.println("Enter Claim Id");

                                claimId = sc.nextInt();

                                claimDao.updateStatus(claimId, ClaimStatus.REJECTED);

                                System.out.println("Claim Rejected");

                                break;
                        }
                    }
            }

        }
        catch(NoResultException e){

            System.out.println("Invalid Credentials");
        }

        sc.close();

        context.close();
    }
}