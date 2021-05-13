package application;

import java.util.Collections;
import java.util.regex.*; 

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TextFieldProperty {
	 
	/**
	 * set the border of the text field to red
	 * 
	 * @param tf
	 */
	public void setRed(TextField tf) {
	    ObservableList<String> styleClass = tf.getStyleClass();

	    if(!styleClass.contains("borderError")) {
	        styleClass.add("borderError");
	    }
	}

	/**
	 * remove the red border from the text field
	 * 
	 * @param tf
	 */
	public void removeRed(TextField tf) {
	    ObservableList<String> styleClass = tf.getStyleClass();
	    styleClass.removeAll(Collections.singleton("borderError"));
	    
	    if(!styleClass.contains("text-field:focused")) {
	    	styleClass.add(".text-field:focused");
	    }
	}
	
	/**
	 * set the border of the text field to red
	 * 
	 * @param tf
	 */
	public void setGreen(TextField tf) {
	    ObservableList<String> styleClass = tf.getStyleClass();

	    if(!styleClass.contains("borderCorrect")) {
	        styleClass.add("borderCorrect");
	    }
	}


	/**
	 * remove the red border from the text field
	 * 
	 * @param tf
	 */
	public void removeGreen(TextField tf) {
	    ObservableList<String> styleClass = tf.getStyleClass();
	    styleClass.removeAll(Collections.singleton("borderCorrect"));
	    
	    if(!styleClass.contains("text-field:focused")) {
	    	styleClass.add(".text-field:focused");
	    }
	}

	/**
	 * 
	 * @param tf
	 * @param maxLength
	 */
	public void setMaxLimit(final TextField tf, final int maxLength) {
	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            
	        	if (tf.getText().length() > maxLength) {
	                String s = tf.getText().substring(0, maxLength);
	                tf.setText(s);
	            }
	        }
	    });
	}
	
	
	public boolean checkIDField(final TextField tf, Label messageLabel) {
		if (tf.getText().isEmpty()) {
			messageLabel.setText("Empty Field");
			removeGreen(tf);
			setRed(tf);
			return false;
		}
		else if (!Pattern.matches("[0-9]{5}", tf.getText())) {
			messageLabel.setText("Incorrect Format");
			removeGreen(tf);
			setRed(tf);
			return false;
		}
		
		messageLabel.setText("");
		removeRed(tf);
		setGreen(tf);
		return true;
	}
	
	public void duplicateDoctorIDError(final TextField tf, Label messageLabel) {
		messageLabel.setText("ID D" + tf.getText() +" is in use");
		removeGreen(tf);
		setRed(tf);
	}
	
	public boolean checkNameField(final TextField tf, Label messageLabel) {
		if (tf.getText().isEmpty()) {
			messageLabel.setText("Empty Field");
			removeGreen(tf);
			setRed(tf);
			return false;
		}
		else if (!Pattern.matches("[A-Z]?[a-z]+", tf.getText())) {
			messageLabel.setText("Incorrect Format");
			removeGreen(tf);
			setRed(tf);
			return false;
		}
		
		messageLabel.setText("");
		removeRed(tf);
		setGreen(tf);
		return true;
	}
	
	
	public boolean checkDateField(final TextField dayTF, final TextField monthTF, final TextField yearTF, Label messageLabel) {
		
		boolean cond = true;
		if (dayTF.getText().isEmpty() || monthTF.getText().isEmpty() || yearTF.getText().isEmpty()) {
			if (dayTF.getText().isEmpty()) {
				removeGreen(dayTF);
				setRed(dayTF);
			}
			else {
				removeRed(dayTF);
				setGreen(dayTF);
			}
			
			if (monthTF.getText().isEmpty()) {
				removeGreen(monthTF);
				setRed(monthTF);
			}
			else {
				removeRed(monthTF);
				setGreen(monthTF);
			}
			
			if (yearTF.getText().isEmpty()) {
				removeGreen(yearTF);
				setRed(yearTF);
			}
			else {
				removeRed(yearTF);
				setGreen(yearTF);
			}
			
			cond &= false;
		}

		if (!Pattern.matches("[0-9]{2}", dayTF.getText()) || !Pattern.matches("[0-9]{2}", monthTF.getText()) || !Pattern.matches("[0-9]{4}", yearTF.getText())) {
			if (!Pattern.matches("[0-9]{2}", dayTF.getText())) {
				removeGreen(dayTF);
				setRed(dayTF);
			}
			else {
				removeRed(dayTF);
				setGreen(dayTF);
			}
			
			if (!Pattern.matches("[0-9]{2}", monthTF.getText())) {
				removeGreen(monthTF);
				setRed(monthTF);
			}
			else {
				removeRed(monthTF);
				setGreen(monthTF);
			}
			
			if (!Pattern.matches("[0-9]{4}", yearTF.getText())) {
				removeGreen(yearTF);
				setRed(yearTF);
			}
			else {
				removeRed(yearTF);
				setGreen(yearTF);
			}
			
			cond &= false;
		}
		
		
		if (!cond) {
			messageLabel.setText("Empty Field(s) or Incorrect Format");
			return false;
		}
		else {
			setGreen(dayTF);
			setGreen(monthTF);
			setGreen(yearTF);
			messageLabel.setText("");
			return true;
		}
	}
	
	public boolean checkCredentialField(final TextField tf, Label messageLabel) {
		if (tf.getText().isEmpty()) {
			messageLabel.setText("Empty Field");
			removeGreen(tf);
			setRed(tf);
			return false;
		}
		else if (!Pattern.matches("[A-Za-z0-9@._-]+", tf.getText())) {
			messageLabel.setText("Incorrect Format");
			removeGreen(tf);
			setRed(tf);
			return false;
		}
		
		messageLabel.setText("");
		removeRed(tf);
		setGreen(tf);
		return true;
	}
	
	public boolean checkAddressTextArea(final TextArea ta, Label messageLabel) {
		if (ta.getText().isEmpty()) {
			messageLabel.setText("Empty Field");
			return false;
		}
		
		messageLabel.setText("");
		return true;
	}
	
	public boolean checkPhoneNumberField(final TextField tf, Label messageLabel) {
		if (tf.getText().isEmpty()) {
			messageLabel.setText("Empty Field");
			removeGreen(tf);
			setRed(tf);
			return false;
		}
		else if (!Pattern.matches("[0-9]{3}[-][0-9]{3}[-][0-9]{4}", tf.getText())) {
			messageLabel.setText("Incorrect Format (e.g. XXX-XXX-XXXX)");
			removeGreen(tf);
			setRed(tf);
			return false;
		}
		
		messageLabel.setText("");
		removeRed(tf);
		setGreen(tf);
		return true;
	}
}
