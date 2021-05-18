/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {
  /**
   * Creates a new ExampleSubsystem.
   */
  CANSparkMax leftFront = new CANSparkMax(11, MotorType.kBrushless);
  CANSparkMax leftBack = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMax rightFront = new CANSparkMax(13, MotorType.kBrushless);
  CANSparkMax rightBack = new CANSparkMax(16, MotorType.kBrushless);

  public Drivetrain() {
    //rightFront.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSpeed(double speed, double throttle, double leftfactor, double rightfactor){
    speed *=throttle;
    leftFront.set(-speed*leftfactor);
    leftBack.set(-speed*leftfactor);
    rightFront.set(-speed*rightfactor);
    rightBack.set(-speed*rightfactor);

  }

  @Override
  protected void initDefaultCommand() {
    // TODO Auto-generated method stub

  }
}
