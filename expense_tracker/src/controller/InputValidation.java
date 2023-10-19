package controller;

import java.util.Arrays;

public class InputValidation {

  public static boolean isValidAmount(double amount) {
    
    // Check if amount is a valid number
    if(amount <= 0 || amount>1000) {
      return false;
    }
    else {
      return true;
    }
  }

  public static boolean isValidCategory(String category) {
    // Check if category is a valid word
    if(category == null) {
      return false; 
    }
    
    if(category.trim().isEmpty()) {
      return false;
    }

    if(!category.matches("[a-zA-Z]+")) {
      return false;
    }
    
    String[] validWords = {"food", "travel", "bills", "entertainment", "other"};
      
    if(!Arrays.asList(validWords).contains(category.toLowerCase())) {
      // invalid word  
      return false;
    }
  
    return true;
  
  }

}