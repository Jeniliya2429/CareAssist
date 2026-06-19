package com.app;


import com.app.config.AppConfig;
import com.app.dao.ClaimDao;
import com.app.dao_impl.ClaimDaoImpl;
import com.app.enums.ClaimStatus;
import com.app.exception.ResourceNotFoundException;
import com.app.model.Claim;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Scanner sc = new Scanner(System.in);

        ClaimDao claimDao = context.getBean(ClaimDaoImpl.class);

        while(true){

            System.out.println("\n===== CLAIM MENU =====");

            System.out.println("1. Add Claim");
            System.out.println("2. Delete Claim");
            System.out.println("3. Update Claim");
            System.out.println("4. Fetch All Claims");
            System.out.println("5. Fetch Claim By Id");
            System.out.println("0. Exit");

            int op = sc.nextInt();

            if(op == 0)
                break;

            switch(op){

                case 1:

                    System.out.println("Enter Amount");
                    double amount = sc.nextDouble();

                    System.out.println("Enter Status(PENDING/APPROVED/REJECTED)");

                    ClaimStatus status = ClaimStatus.valueOf(sc.next().toUpperCase());

                    claimDao.insert(new Claim(amount, status));

                    break;

                case 2:

                    System.out.println("Enter Claim Id to delete");

                    int id = sc.nextInt();

                    try{

                        claimDao.deleteById(id);

                    }
                    catch(ResourceNotFoundException e){

                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:

                    System.out.println(
                            "Enter Claim Id to update");

                    try{

                        Claim claim = claimDao.getById(sc.nextInt());

                        System.out.println("Existing Claim Record");

                        System.out.println(claim);

                        System.out.println("Enter New Amount");

                        claim.setAmount(sc.nextDouble());

                        System.out.println("Enter New Status");

                        claim.setStatus(ClaimStatus.valueOf(sc.next().toUpperCase()));

                        claimDao.update(claim);

                    }
                    catch(EmptyResultDataAccessException e){

                        System.out.println("Invalid Claim Id");
                    }

                    break;

                case 4:

                    claimDao.getAll().forEach(System.out::println);

                    break;

                case 5:

                    System.out.println("Enter Claim Id");

                    id = sc.nextInt();

                    try{

                        Claim claim = claimDao.getById(id);

                        System.out.println(claim);

                    }
                    catch(EmptyResultDataAccessException e){

                        System.out.println("Invalid Claim Id");
                    }

                    break;

                default:

                    System.out.println("Invalid Option");
            }
        }

        sc.close();

        context.close();
    }
}