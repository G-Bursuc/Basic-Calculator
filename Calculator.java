import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class Calculator extends JFrame {

	// Global variables
	String operator = null; // store type of operator being chosen e.g. plus, minus
	String lastInput = ""; // stores the last type of input user made e.g. number, operator
	double firstNum, secondNum = 0; // store the first and second number of calculation
	double memoryNum = 0; // store the number for memory function

	JTextField display; // displays the number on screen

	public Calculator() {
		// CREATE & INITIALISE ELEMENTS
		// Create panel
		JPanel panel = new JPanel(new MigLayout());

		// Create Buttons
		// Part 1 - Number & Basic Operator Buttons
		JButton button1 = new JButton("1");
		JButton button2 = new JButton("2");
		JButton button3 = new JButton("3");
		JButton button4 = new JButton("4");
		JButton button5 = new JButton("5");
		JButton button6 = new JButton("6");
		JButton button7 = new JButton("7");
		JButton button8 = new JButton("8");
		JButton button9 = new JButton("9");
		JButton button0 = new JButton("0");
		JButton buttonPlus = new JButton("+");
		JButton buttonMinus = new JButton("-");
		JButton buttonMulti = new JButton("*");
		JButton buttonDiv = new JButton("/");
		JButton buttonEquals = new JButton("=");
		JButton buttonDot = new JButton(".");
		JButton buttonBack = new JButton("←");
		JButton buttonCE = new JButton("CE");
		JButton buttonC = new JButton("C");

		// Part 2 - Memory Buttons
		JButton buttonMC = new JButton("MC");
		JButton buttonMR = new JButton("MR");
		JButton buttonMS = new JButton("MS");
		JButton buttonMemPlus = new JButton("M+");
		JButton buttonMemMinus = new JButton("M-");

		// Part 3 - Complex Operator Buttons
		JButton buttonPlusMinus = new JButton("±");
		JButton buttonRoot = new JButton("√");
		JButton buttonPercent = new JButton("%");
		JButton buttonPowerOf = new JButton("^");
		JButton buttonInvert = new JButton("1/x");

		// Create and setup the text field
		display = new JTextField();
		display.setText("0");
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setEditable(false);

		// ADDING ELEMENTS TO PANEL
		// Adding text field
		panel.add(display, "wrap, grow, spanx"); // add display to the top

		// First row
		panel.add(buttonMC, "grow"); // grow makes button take up entire cell space
		panel.add(buttonMR, "grow");
		panel.add(buttonMS, "grow");
		panel.add(buttonMemPlus, "grow");
		panel.add(buttonMemMinus, "grow, wrap"); // wrap will move next cell added to next row

		// Second row
		panel.add(buttonBack, "grow");
		panel.add(buttonCE, "grow");
		panel.add(buttonC, "grow");
		panel.add(buttonPlusMinus, "grow");
		panel.add(buttonRoot, "grow, wrap");

		// Third row
		panel.add(button7, "grow");
		panel.add(button8, "grow");
		panel.add(button9, "grow");
		panel.add(buttonDiv, "grow");
		panel.add(buttonPercent, "grow, wrap");

		// Fourth row
		panel.add(button4, "grow");
		panel.add(button5, "grow");
		panel.add(button6, "grow");
		panel.add(buttonMulti, "grow");
		panel.add(buttonPowerOf, "grow, wrap");

		// Fifth row
		panel.add(button1, "grow");
		panel.add(button2, "grow");
		panel.add(button3, "grow");
		panel.add(buttonMinus, "grow");
		panel.add(buttonInvert, "grow, wrap");

		// Sixth row
		panel.add(button0, "grow, span 2");
		panel.add(buttonDot, "grow");
		panel.add(buttonPlus, "grow");
		panel.add(buttonEquals, "grow");

		// JFrame setup
		add(panel);
		setSize(296, 245);
		setTitle("Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		// ACTION LISTENERS FOR EVERY BUTTON
		// NUMBER BUTTONS
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				chooseNum(1); // calls a method with the specific number as parameter
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				chooseNum(2);
			}
		});
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				chooseNum(3);
			}
		});
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				chooseNum(4);
			}
		});
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				chooseNum(5);
			}
		});
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				chooseNum(6);
			}
		});
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				chooseNum(7);
			}
		});
		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				chooseNum(8);
			}
		});
		button9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				chooseNum(9);
			}
		});
		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				chooseNum(0);
			}
		});

		// OPERATOR BUTTONS
		buttonPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				simpleOp("plus"); // calls simple operator method with specific operator function
			}
		});
		buttonMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				simpleOp("minus");
			}
		});
		buttonMulti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				simpleOp("multi");
			}
		});
		buttonDiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				simpleOp("div");
			}
		});

		// MISC BUTTONS
		// Initiates the calculation
		buttonEquals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// only executes if first number is input and an operator is chosen
				if (firstNum != 0 && operator != null) {
					secondNum = Double.parseDouble(getDisplay()); // store number on display into second number
					calculate(); // do the calculation

					lastInput = "equal"; // update last input to be type equal
				}
			}
		});

		// Inputs a dot in display for decimal numbers
		buttonDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// only one dot can be input, checks for a dot in display
				if (!getDisplay().contains("."))
					setDisplay(getDisplay() + ".");
			}
		});

		// Delete a number on the display
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// put display contents in a string and delete the last character
				String text = getDisplay().substring(0, getDisplay().length() - 1);

				// Error checking:
				// if the user deletes with one number remaining, default display to 0
				if (text.equals(""))
					resetDisplay(); // sets display to 0 (default)
				// if the user decides to delete the decimal point, e.g. backspace on 23.1
				else if (text.charAt((text.length()) - 1) == '.') // compare last character to see if it's a dot
					setDisplay(text.substring(0, text.length() - 1)); // delete last character again
				else // if all other error checks fail then update display
					setDisplay(text);
			}
		});

		// Resets number on screen only
		buttonCE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetDisplay(); // set display to default 0
			}
		});

		// Clears/"resets" the calculator
		buttonC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// reset all calculator variables
				reset();
			}
		});

		// MEMORY BUTTONS (Part 2)
		// Set the memory number to 0/clear the memory variable
		buttonMC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				memoryNum = 0;
			}
		});

		// Set the display to memory number
		buttonMR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (memoryNum != 0) // make sure memory has a number
					setDisplay(memoryNum);
			}
		});

		// Set the display number to memory number
		buttonMS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (getDisplayNum() != 0) // make sure valid number is input
					memoryNum = getDisplayNum();
			}
		});

		// Add number displayed to memory number
		buttonMemPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// make sure memory has a number and display has a valid number
				if (getDisplayNum() != 0 && memoryNum != 0) {
					// assign first and second numbers
					firstNum = memoryNum;
					secondNum = getDisplayNum();
					operator = "plus"; // set the operator type
					calculate(); // calculate
					memoryNum = getDisplayNum(); // store result in memory number
					lastInput = "equals";
				}

			}
		});

		// Take away display number from memory number
		buttonMemMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// same as memPlus, except operator used is a minus instead
				if (getDisplayNum() != 0 && memoryNum != 0) {
					firstNum = memoryNum;
					secondNum = getDisplayNum();
					operator = "minus";
					calculate();
					memoryNum = getDisplayNum();
					lastInput = "equals";
				}
			}
		});

		// OTHER FUNCTION BUTTONS (Part 3)
		buttonPlusMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				complexOp("plusminus"); // call complex operator method, deals with calculations with one number
										// involved or is percent calculation
			}
		});
		buttonRoot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				complexOp("root");
			}
		});
		buttonPercent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				calcPercent(); // call separate method for dealing with percents
			}
		});
		buttonPowerOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				simpleOp("powerOf"); // powerOf involves two numbers, use simpleOp
			}
		});
		buttonInvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				complexOp("invert");
			}
		});

	}

	// CALCULATOR METHODS

	// When an simple operator is pressed (e.g. plus, minus)
	public void simpleOp(String op) {
		// if the last input was a number or the equals button
		if (lastInput.equals("number") || lastInput.equals("equal")) {
			// Two scenarios possible:
			// 1) operator hasn't been set yet - beginning of a new calculation: store the
			// first number and operator
			// 2) operator has been set, meaning two numbers have been input and a third is
			// being introduced: store the second number, do calculation, display the
			// result,
			// save display as the first number and update operator.
			if (getDisplayNum() != 0) { // make sure valid number is on display
				if (operator == null) { // if this is a new calculation
					firstNum = getDisplayNum(); // set displayed number into firstNum
					operator = op; // set the operation to be executed
				} else if (operator != null) { // else do calculation if user presses another operator
					secondNum = getDisplayNum(); // store displayed number into secondNum
					calculate(); // do calculation and display result
					operator = op; // update operation for next calculation
					firstNum = getDisplayNum(); // result from last calculation into firstNum since user wants to
												// use this with another number
				}
				lastInput = "operator"; // update last input to operator
			}
		} else if (lastInput.equals("operator")) { // if the last input was an operator
			operator = op; // update the operator to the new one
		}
	}

	// Deals with complex calculations: only involve one number
	public void complexOp(String op) {
		if (getDisplayNum() != 0) { // make sure a number has been input
			firstNum = getDisplayNum(); // store display to firstNum
			operator = op; // set operation
			calculate(); // calculate
			firstNum = getDisplayNum(); // store display into firstNum
			lastInput = "operator"; // update last input
			// else, if invert was chosen and no number was input, display error
		} else if (op.equals("invert") && getDisplayNum() == 0)
			setDisplay("ERROR");
	}

	// Deals with percent calculations
	public void calcPercent() {
		String validOps = "plus, minus, multi, div"; // list of valid operators to be used with percent
		// make sure valid number is input, prior number has been input and a valid
		// operator is set
		if (getDisplayNum() != 0 && firstNum != 0 && validOps.contains(operator)) {
			setDisplay((firstNum * (getDisplayNum() / 100))); // display the percent of first number, user can choose to
																// press equals or another operator or keep adding the
																// percentage
			lastInput = "number";
		}
	}

	// Chooses which number has been pressed to go onto display
	public void chooseNum(int num) {
		// if the last input was an operator, that means this is a new number
		// OR display is 0 (empty)
		if (lastInput.equals("operator") || getDisplayNum() == 0)
			setDisplay(num); // clear display and set display to the number
		else if (lastInput.equals("number")) // otherwise, it is adding to existing numbers
			addToDisplay(num);
		else if (lastInput.equals("equal")) // if the equal button was pressed
			setDisplay(num); // set display to number that was pressed, setup new calculation

		lastInput = "number"; // set the last input to be a number
	}

	// Calculates the numbers user put in
	public void calculate() {
		double result = 0; // store final result
		switch (operator) { // checks which operator was chosen and does calculation
		case "plus":
			result = firstNum + secondNum;
			break;
		case "minus":
			result = firstNum - secondNum;
			break;
		case "multi":
			result = firstNum * secondNum;
			break;
		case "div":
			result = firstNum / secondNum;
			break;
		case "plusminus":
			result = firstNum * -1;
			break;
		case "root":
			result = Math.sqrt(firstNum);
			break;
		case "powerOf":
			result = Math.pow(firstNum, secondNum);
			break;
		case "invert":
			result = 1 / firstNum;
			break;
		}

		reset(); // resets calculator for next function

		setDisplay(result); // display the result
	}

	// HELPER METHODS

	// Resets the calculation variables and resets display
	public void reset() {
		firstNum = secondNum = 0; // reset to default states
		operator = null; // reset operation for next calculation
		resetDisplay();
	}

	// Sets the display content to blank
	public void clearDisplay() {
		display.setText("");
	}

	// Returns content of display as a string
	public String getDisplay() {
		return display.getText();
	}

	// Returns content of display as a double
	public double getDisplayNum() {
		// if the display shows error, return 0 instead of crashing
		if (getDisplay().equals("ERROR"))
			return 0;
		else
			return Double.parseDouble(getDisplay());
	}

	// Sets the display to a specified number
	public void setDisplay(double number) {
		// if number matches an integer casted number, then display number as integer
		// (e.g. display 2 instead of 2.0)
		if (number == ((int) number))
			display.setText(String.valueOf((int) number));
		else // otherwise, number involves decimal
			display.setText(String.valueOf(number));
	}

	// Sets the display to a specified string
	public void setDisplay(String text) {
		display.setText(text);
	}

	// Adds a number to the end of the display
	public void addToDisplay(int number) {
		display.setText(display.getText() + number);
	}

	// Reset the display to its "default" state of 0
	public void resetDisplay() {
		display.setText("0");
	}

	public static void main(String[] args) {
		new Calculator();
	}

}
