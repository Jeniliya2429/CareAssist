package com.controller;

import com.config.HibernateConfig;
import com.enums.ClaimStatus;
import com.enums.Role;
import com.exception.ResourceNotFoundException;
import com.model.Claim;
import com.model.User;
import com.service.AuthService;
import com.service.ClaimService;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {

        Session session = HibernateConfig.getSessionFactory().openSession();

        Scanner sc = new Scanner(System.in);

        ClaimService claimService = new ClaimService(session);

        AuthService authService = new AuthService(session);

        System.out.println("LOGIN");

        System.out.print("Enter Email: ");
        String email = sc.next();

        System.out.print("Enter Password: ");
        String password = sc.next();

        try {

            User user = authService.login(email, password);

            switch(user.getRole()) {

                case PATIENT:

                    System.out.println("\n PATIENT MENU ");

                    while(true) {

                        System.out.println("1. Add Claim");
                        System.out.println("2. Delete Claim");
                        System.out.println("3. Fetch All Claims");
                        System.out.println("4. Fetch Claim By ID");
                        System.out.println("0. Exit");

                        int op = sc.nextInt();

                        if(op == 0)
                            break;

                        switch(op) {

                            case 1:

                                Claim claim = new Claim();

                                System.out.print("Enter Claim Amount: ");

                                claim.setAmount(sc.nextDouble());

                                claim.setStatus(ClaimStatus.PENDING);

                                claimService.addClaim(claim,email);;

                                System.out.println("Claim Added Successfully");

                                break;

                            case 2:

                                System.out.print("Enter Claim ID to delete: ");

                                int id = sc.nextInt();

                                try {

                                    claimService.deleteById(id);

                                    System.out.println("Claim Deleted");

                                }
                                catch(ResourceNotFoundException e) {

                                    System.out.println(e.getMessage());
                                }

                                break;

                            case 3:

                                System.out.println("\n----- ALL CLAIMS -----");

                                List<Claim> list = claimService.getAllClaims();

                                list.forEach(System.out::println);

                                break;

                            case 4:

                                System.out.print("Enter Claim ID: ");

                                id = sc.nextInt();

                                try {

                                    Claim claimFound = claimService.getById(id);

                                    System.out.println(claimFound);

                                }
                                catch(ResourceNotFoundException e) {

                                    System.out.println(e.getMessage());
                                }

                                break;

                            default:

                                System.out.println("Invalid Option");
                        }
                    }

                    break;

                case PROVIDER:

                    System.out.println("Welcome Provider");

                    break;

                case INSURANCE_COMPANY:

                    System.out.println("Welcome Insurance Company");

                    break;

                case ADMIN:

                    System.out.println("Welcome Admin");

                    break;

                default:

                    System.out.println("Invalid Role");
            }

        }
        catch(NoResultException e) {

            System.out.println("Invalid Credentials");
        }

        sc.close();
        session.close();
    }
}