/*
 * Author: Jordan Kuyon
 * Purpose of Program: A graphical user interface with temperature 
 * and distance converter functionality.
 * Date: 06/26/2023
 */

import javax.swing.*; // Importing Java Swing Library
import java.awt.*; // Importing Abstract Window Toolkit
import java.awt.event.ActionEvent; // Importing Action Event Class
import java.awt.event.ActionListener; // Importing Action Listeners
import java.text.DecimalFormat; // Importing Decimal Formatter

class Converter {
	
	// Private Variable for User Input - Encapsulation
    private double input;

    // Constructor
    public Converter() {
        this.input = Double.NaN; // For validation if input is not a number
    }

    // Overloading Constructor
    public Converter(double input) {
        this.input = input;
    }

    // Getter for user input
    public double getInput() {
        return input;
    }

    // Method to return converted value as a double
    public double convert() {
        return input;
    }
    
 // Method to create GUI Window and Elements
    void createAndShowGUI() {
        // Create and configure the main frame
        JFrame frame = new JFrame("GUI Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200); // Frame Size
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Create the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Create the buttons
        JButton distanceButton = new JButton("Distance");
        JButton temperatureButton = new JButton("Temperature");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to use button to convert distance
        distanceButton.addActionListener(new ActionListener() {
        	// Overriding Method
            @Override
            public void actionPerformed(ActionEvent e) {
                distanceConversion();
            }
        });
        
     // Add action listeners to use button to convert temperature
        temperatureButton.addActionListener(new ActionListener() {
        	// Overriding Method
            @Override
            public void actionPerformed(ActionEvent e) {
                temperatureConversion(); // Calling temperature conversion method
            }
        });

        // Add action listener to exit button to escape the program
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	// Open message dialog to inform GUI exit
            	JOptionPane.showMessageDialog(null, "The window is now closing. ",
        				"Exit", JOptionPane.INFORMATION_MESSAGE);
                
            	System.exit(0); // Exit the GUI windows and close
            }
        });

        // Add the buttons to the panel
        panel.add(distanceButton); // Distance Button
        panel.add(temperatureButton); // Temperature Button
        panel.add(exitButton); // Exit Button

        // Add the panel to the frame
        frame.getContentPane().add(panel);

        // Display the frame
        frame.setVisible(true);
    }
    
 // Method to prompt for distance in miles and convert to kilometers and display
    private static void distanceConversion() {
    	
    	// Distance Prompt
        String input = JOptionPane.showInputDialog(null, "Input distance in miles to convert to kilometers:", "Input",
                JOptionPane.PLAIN_MESSAGE);
        
        if (input != null) {
        	try { // Run Program        		
        		
        		double distance = Double.parseDouble(input); // Convert input to Double
        		DistanceConverter converter = new DistanceConverter(distance); // Create instance of DistanceConverter Class
        		double convertedDistance = converter.convert(); // Distance in km
        		
        		// Format converted distance to three decimal places
        		DecimalFormat df = new DecimalFormat("#.###");
        		String formattedDistance = df.format(convertedDistance);
        		
        		// Display Message Dialog for Distance
        		JOptionPane.showMessageDialog(null, input + " miles equals " + formattedDistance + " kilometers.",
        				"Message", JOptionPane.INFORMATION_MESSAGE);
        		
        	} catch (NumberFormatException e) { // Throw error message for invalid distance input
        		JOptionPane.showMessageDialog(null, "Invalid distance entered. Please enter a valid numeric value:",
                        "Input", JOptionPane.ERROR_MESSAGE);
        	}
        }
    }

    // Method to prompt for temperature in Fahrenheit and convert to Celsius and display
    private static void temperatureConversion() {
    	
    	// Temperature Prompt
        String input = JOptionPane.showInputDialog(null, "Input Fahrenheit temp to convert to Celsius:", "Input",
                JOptionPane.PLAIN_MESSAGE);
        
        if (input != null) {
        	try { // Run Program
        		
        		double temperature = Double.parseDouble(input); // Convert input to Double
        		TemperatureConverter converter = new TemperatureConverter(temperature);
        		double convertedTemperature = converter.convert();
        		
        		// Format converted temperature to three decimal places
        		DecimalFormat df = new DecimalFormat("#.###");
        		String formattedTemperature = df.format(convertedTemperature);
        		
        		// Display Message dialog for temperature
        		JOptionPane.showMessageDialog(null, input + " F equals " + formattedTemperature + " °C.",
        				"Message", JOptionPane.INFORMATION_MESSAGE); 
        		
        	} catch (NumberFormatException e) { // Throw error message for invalid temperature input
        		JOptionPane.showMessageDialog(null, "Invalid temperature entered. Please enter a valid numeric value:",
                        "Input", JOptionPane.ERROR_MESSAGE);
        	}
        }
    }
}

// 1st Child Class to store temperature object
class TemperatureConverter extends Converter {
	
	// Constructor
    public TemperatureConverter() {
        super();
    }
    
    // Overloading Constructor
    public TemperatureConverter(double input) {
        super(input); // Invoke user input for temperature
    }

    // Overriding Method to convert temperature value
    @Override
    public double convert() {
        if (Double.isNaN(getInput()))
            return Double.NaN;
        else
            return ((getInput() - 32) * 5) / 9; // Celsius Conversion
    }
}

// 2nd Child Class to store distance object
class DistanceConverter extends Converter {
	
	// Constructor
    public DistanceConverter() {
        super();
    }

    // Overloading Constructor
    public DistanceConverter(double input) {
        super(input); // Invoke user input for distance
    }

    // Overriding Method to convert distance value
    @Override
    public double convert() {
        if (Double.isNaN(getInput()))
            return Double.NaN;
        else
            return getInput() * 1.60934; // Kilometer Conversion
    }
}

// Class Definition
public class TempDistConverter {
	
	// Main Method
    public static void main(String[] args) {
    	
    	// Create instance of the Converter Class
    	Converter converter = new Converter();
    	
    	// Call createAndShowGUI from Method in Parent
        converter.createAndShowGUI();
    }
}





