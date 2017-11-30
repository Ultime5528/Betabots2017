package org.usfirst.frc.team5528.robot.triggers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.*;

/**
 * <p>Trigger qui combine une flèche du DPad avec un bouton d'un XBoxController.</p>
 * 
 * Exemple (flèche droite avec bouton B) :  
 * <p><code>
 * ArrowCombination droiteB = new ArrowCombination(gamepad, Arrow.LEFT, XboxButton.B); <br/>
 * droiteB.whenPressed(new MyCommand());
 * </code></p>
 */
public class ArrowCombination extends Button {

	public interface Button {
		public int getButton();
	}
	
	public enum Arrow {
		
		NONE(-1),
		LEFT(270),
		RIGHT(90);
		
		private int angle;
		private Arrow(int angle) {
			this.angle = angle;
		}
		
		private int getAngle() {
			return angle;
		}
		
	}
	
	public enum XboxButton implements Button {
		
		A(1),
		B(2),
		X(3),
		Y(4),
		LB(5),
		RB(6);
		
		private int button;
		private XboxButton(int button) {
			this.button = button;
		}
		
		@Override
		public int getButton() {
			return button;
		}
		
	}
	
	private GenericHID hid;
	private Arrow arrow;
	private Button button;
	
	public ArrowCombination(GenericHID hid, Arrow arrow, Button button) {
		this.hid = hid;
		this.arrow = arrow;
		this.button = button;
	}
	
    public boolean get() {
        return hid.getRawButton(button.getButton()) && hid.getPOV() == arrow.getAngle();
    }
}
