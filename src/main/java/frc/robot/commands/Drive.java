/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * An example command that uses an example subsystem.
 */
public class Drive extends Command {

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Drive() {

    requires(Robot.drivetrain);
  }

  Joystick stick = new Joystick(0);
  XboxController xbox = new XboxController(0);

  double leftfactor = 1;
  double rightfactor = 1;
  double speed = 0;
  double throttlefactor = .8;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   /* Robot.drivetrain.setSpeed(stick.getY() * throttleFactor(), stick.getZ()*throttleFactor());
    DriverStation.reportError("its working", true);*/
    speed = -(deadband(xbox.getTriggerAxis(Hand.kRight)) - deadband(xbox.getTriggerAxis(Hand.kLeft)));

    if(xbox.getBumper(Hand.kRight)){
      throttlefactor = .95;
    }
    else{
      throttlefactor = .8;
    }

    if(deadband(xbox.getX(Hand.kLeft))>0){
      leftfactor = 1;
      rightfactor = getFactorValue(xbox.getX(Hand.kLeft));
    }
    else if(deadband(xbox.getX(Hand.kLeft))<0){
      rightfactor = 1;
      leftfactor = getFactorValue(xbox.getX(Hand.kLeft));
    }

    if(xbox.getAButton()){
      speed = 0;
      leftfactor=0;
      rightfactor=0;
    }
    

    Robot.drivetrain.setSpeed(speed,throttlefactor,leftfactor,rightfactor);
  }

  private double getFactorValue(double triggerAxis) {
    return (triggerAxis>0) ? -2*triggerAxis+1 : 2*triggerAxis+1;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  private double throttleFactor(){
    return -0.5*stick.getRawAxis(3)+.5;
  }

  private double getThrottleXbox(){
    return throttlefactor;
  }

  double deadband(double dooble){
    if(dooble <.1 && dooble>-.1){
      return 0;
    }
    return dooble;
  }
}
