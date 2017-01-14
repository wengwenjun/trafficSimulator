package myproject.main;


import myproject.ui.TrafficShow;
import myproject.ui.UI;
import myproject.ui.UIMenu;
import myproject.ui.UIMenuAction;
import myproject.ui.UIMenuBuilder;
import myproject.ui.UIError;
import myproject.ui.UIForm;
import myproject.ui.UIFormTest;
import myproject.ui.UIFormBuilder;



import javax.swing.JFrame;

import myproject.parameters.Parameters;
/**
 * The control class to use the UI
 * @author wenjun
 *
 */
class Control {
  private static final int EXITED = 0;
  private static final int EXIT = 1;
  private static final int START = 2;
  private static final int NUMSTATES = 10;
  private UIMenu[] _menus;
  private int _state;

  private UIForm _getTrafficForm;
  private UIFormTest _numberTest;
  private UIFormTest _stringTest;
  private UIFormTest _doubleTest;

  private UI _ui=null;
  
  Control(UI ui) {
    _ui = ui;

    _menus = new UIMenu[NUMSTATES];
    _state = START;
    addSTART(START);
    addEXIT(EXIT);
    
    UIFormTest indexTest = new UIFormTest() {
        public boolean run(String input) {
          try {
            int i = Integer.parseInt(input);
            return i > 0 && i < 4;
          } catch (NumberFormatException e) {
            return false;
          }
        }
      };
    _numberTest = new UIFormTest() {
        public boolean run(String input) {
          try {
            Integer.parseInt(input);
            return true;
          } catch (NumberFormatException e) {
            return false;
          }
        }
      };
      _doubleTest = new UIFormTest() {
          public boolean run(String input) {
            try {
              Double.parseDouble(input);
              return true;
            } catch (NumberFormatException e) {
              return false;
            }
          }
        };
    _stringTest = new UIFormTest() {
        public boolean run(String input) {
          return ! "".equals(input.trim());
        }
      };
  
  }
  
  void run() {
    try {
      while (_state != EXITED) {
        _ui.processMenu(_menus[_state]);
      }
    } catch (UIError e) {
      _ui.displayError("UI closed");
    }
  }
  
  private void addSTART(int stateNum) {
    UIMenuBuilder m = new UIMenuBuilder();
    
    m.add("Default",
      new UIMenuAction() {
        public void run() {
          _ui.displayError("No support operator!");
        }
      });
    m.add("RunSimulation",
      new UIMenuAction() {
        public void run() {
    		JFrame frame  =   new  JFrame( "Traffic" );
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            if(TrafficShow.singleton!=null)
            	System.exit(-1);
            frame.add( new  TrafficShow());
            frame.pack();
            frame.setVisible(true);
            try {
				Thread.sleep((long)Parameters.timeOut*1000);
				frame.setVisible(false);
				TrafficShow.singleton=null;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
      });

    m.add("Configure the arguments",
    	      new UIMenuAction() {
    	        public void run() {
    	          
    	          UIFormBuilder f = new UIFormBuilder();
    	          //the 16 configures.
    	          String comment="1.Show current values\n"
    	          		+ "2.Simulation time step\n"
    	          		+ "3.Simulation runtime\n"
    	          		+ "4.Grid size\n"
    	          		+ "5.Set traffic pattern\n"
    	          		+ "6.Set car entry rate\n"
    	          		+ "7.Set road lengths\n"
    	          		+ "8.Set intersection lengths\n"
    	          		+ "9.Set car length\n"
    	          		+ "10.Set max car velocity\n"
    	          		+ "11.Set car stop distance\n"
    	          		+ "12.Set car break distance\n"
    	          		+ "13.Set traffic light green times\n"
    	          		+ "14.Set traffic light yellow times\n"
    	          		+ "15.Reset Simulation and return to main menu\n"
    	          		+ "16.Return to main menu\n";
    	          
    	          f.add("Please choose the selection\n"+comment, _numberTest);
    	          String[] result2 = _ui.processForm(f.toUIForm(""));
    	          //System.out.println(result2[0]);                              
    	          int selection=Integer.parseInt(result2[0]);
    	          menuSelection(selection);
    	        }
    	      });

          
    m.add("Exit",
      new UIMenuAction() {
        public void run() {
          _state = EXIT;
        }
      });
    
    
    
    _menus[stateNum] = m.toUIMenu("Traffic");
  }
  
  private void addEXIT(int stateNum) {
    UIMenuBuilder m = new UIMenuBuilder();
    
    m.add("Default", new UIMenuAction() { public void run() {} });
    m.add("Yes",
      new UIMenuAction() {
        public void run() {
          _state = EXITED;
        }
      });
    m.add("No",
      new UIMenuAction() {
        public void run() {
          _state = START;
        }
      });
    
    _menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
  }
  private void menuSelection(int selection)
  {
	  UIFormBuilder f = new UIFormBuilder();
	  switch (selection){
	  	case 1:
	  		String result="";
	  		result+="Simulation time step(secounds)["+(Parameters.timeStep/1000.0)+"]\n"
	  				+"Simulation run time(secounds)["+Parameters.timeOut+"]\n"
	  				+"Grid size(number of roads)[row="+Parameters.roadRow
	  				+",column"+Parameters.roadColumn+"]\n"
	  				+"Traffic pattern["+ Parameters.trafficPattern +"]\n"
	  				+"Car entry rate(seconds/car)[min="+Parameters.carGeneratorDelayMin
	  				+",max="+Parameters.carGeneratorDelayMax+"]\n"
	  				+"Road segment length (meters)[min="+Parameters.segmentLengthMin
	  				+",max="+Parameters.segmentLengthMax+"]\n"
	  				+"Intersection length (meters)[min="+Parameters.intersectionLengthMin
	  				+",max="+Parameters.intersectionLengthMax+"]\n"
	  				+"Car length(meters)[min="+Parameters.lengthMin
	  				+",max="+Parameters.lengthMax+"]\n"
	  				+"Car maximum velocity(meters/second)[min="+Parameters.maxVelocityMin
	  				+",max="+Parameters.maxVelocityMax+"]\n"
	  				+"Car stop distance(meters)[min="+Parameters.stopDiatanceMin
	  				+",max="+Parameters.stopDiatanceMax+"]\n"
	  				+"Car brake distance(meters)[min="+Parameters.brakeDistanceMin
	  				+",max="+Parameters.brakeDistanceMax+"]\n"
	  				+"Traffic light green time(seconds)[min="+Parameters.greenLightMin
	  				+",max="+Parameters.greenLightMax+"]\n"
	  				+"Traffic light yellow time(seconds)[min="+Parameters.yellowLightMin
	  				+",max="+Parameters.yellowLightMax+"]\n";
	  		_ui.displayMessage(result);
	  		break;
	  	case 2:
	        f.add("The simulation time step", _doubleTest);
	        String[] result2 = _ui.processForm(f.toUIForm(""));
	  		double time=Double.parseDouble(result2[0]);
	  		Parameters.timeStep=(int) (time*1000);
	  		break;
	  	case 3:
	        f.add("The simulation time", _numberTest);
	        String[] result3 = _ui.processForm(f.toUIForm(""));
	  		int runTime=Integer.parseInt(result3[0]);
	  		Parameters.timeOut=runTime;
	  		break;
	  	case 4:
	        f.add("The Grad size column", _numberTest);	  		
	  		f.add("The Grad size row", _numberTest);
	        String[] result4 = _ui.processForm(f.toUIForm(""));
	        int column=Integer.parseInt(result4[0]);
	  		int row=Integer.parseInt(result4[1]);
	  		Parameters.roadRow=row;
	  		Parameters.roadColumn=column;
	  		break;
	  	case 5:
	  		f.add("The simulation pattern", _stringTest);
	        String[] result5 = _ui.processForm(f.toUIForm(""));
	  		Parameters.trafficPattern=result5[0];
	  		break;
	  	case 6:
	  		f.add("The car generation time min", _doubleTest);
	  		f.add("The car generation time max", _doubleTest);
	  		String[] result6 = _ui.processForm(f.toUIForm(""));
	  		Parameters.carGeneratorDelayMin=Double.parseDouble(result6[0]);
	  		Parameters.carGeneratorDelayMax=Double.parseDouble(result6[1]);
	  		break;
	  	case 7:
	  		f.add("The road length min", _doubleTest);
	  		f.add("The road length max", _doubleTest);
	  		String[] result7 = _ui.processForm(f.toUIForm(""));
	  		Parameters.segmentLengthMin=Double.parseDouble(result7[0]);
	  		Parameters.segmentLengthMax=Double.parseDouble(result7[1]);
	  		break;
	  	case 8:
	  		f.add("The road intersection length min", _doubleTest);
	  		f.add("The road intersection length max", _doubleTest);
	  		String[] result8 = _ui.processForm(f.toUIForm(""));
	  		Parameters.intersectionLengthMin=Double.parseDouble(result8[0]);
	  		Parameters.intersectionLengthMax=Double.parseDouble(result8[1]);
	  		break;
	  	case 9:
	  		f.add("The car length min", _doubleTest);
	  		f.add("The car length max", _doubleTest);
	  		String[] result9 = _ui.processForm(f.toUIForm(""));
	  		Parameters.lengthMin=Double.parseDouble(result9[0]);
	  		Parameters.lengthMax=Double.parseDouble(result9[1]);
	  		break;
	  	case 10:
	  		f.add("The car's max velocity min", _doubleTest);
	  		f.add("The car's max velocity max", _doubleTest);
	  		String[] result10 = _ui.processForm(f.toUIForm(""));
	  		Parameters.maxVelocityMin=Double.parseDouble(result10[0]);
	  		Parameters.maxVelocityMax=Double.parseDouble(result10[1]);
	  		break;
	  	case 11:
	  		f.add("The car's stop distance min", _doubleTest);
	  		f.add("The car's stop distance max", _doubleTest);
	  		String[] result11 = _ui.processForm(f.toUIForm(""));
	  		Parameters.stopDiatanceMin=Double.parseDouble(result11[0]);
	  		Parameters.stopDiatanceMax=Double.parseDouble(result11[1]);
	  		break;
	  	case 12:
	  		f.add("The car's break distance min", _doubleTest);
	  		f.add("The car's break distance max", _doubleTest);
	  		String[] result12 = _ui.processForm(f.toUIForm(""));
	  		Parameters.brakeDistanceMin=Double.parseDouble(result12[0]);
	  		Parameters.brakeDistanceMax=Double.parseDouble(result12[1]);
	  		break;
	  	case 13:
	  		f.add("The traffic green light time min", _doubleTest);
	  		f.add("The traffic green light time max", _doubleTest);
	  		String[] result13 = _ui.processForm(f.toUIForm(""));
	  		Parameters.greenLightMin=Double.parseDouble(result13[0]);
	  		Parameters.greenLightMax=Double.parseDouble(result13[1]);
	  		break;
	  	case 14:
	  		f.add("The traffic yellow light time min", _doubleTest);
	  		f.add("The traffic yellow light time max", _doubleTest);
	  		String[] result14 = _ui.processForm(f.toUIForm(""));
	  		Parameters.yellowLightMin=Double.parseDouble(result14[0]);
	  		Parameters.yellowLightMax=Double.parseDouble(result14[1]);
	  		break;
	  	case 15:
	  		Parameters.timeStep=10;//in million seconds
	  		Parameters.runTime=10;//in seconds
	  		Parameters.roadColumn=2;
	  		Parameters.roadRow=2;
	  		
	  		Parameters.carGeneratorDelayMin=2.0;
	  		Parameters.carGeneratorDelayMax=25.0;

	  		Parameters.segmentLengthMin=200;
	  		Parameters.segmentLengthMax=300;
	  		Parameters.intersectionLengthMin=10;
	  		Parameters.intersectionLengthMax=25;
	  		Parameters.lengthMin=10;
	  		Parameters.lengthMax=15;
	  			
	  		Parameters.maxVelocityMin=10;
	  		Parameters.maxVelocityMax=30;
	  		Parameters.stopDiatanceMin=0.5;
	  		Parameters.stopDiatanceMax=5.0;
	  		Parameters.brakeDistanceMin=9;
	  		Parameters.brakeDistanceMax=10;
	  		
	  		Parameters.greenLightMin=30.0;
	  		Parameters.greenLightMax=180.0;
	  		Parameters.yellowLightMin=4.0;
	  		Parameters.yellowLightMax=5.0;
	  		
	  		Parameters.screenX=1024;
	  		Parameters.screenY=768;
	  		Parameters.picturePixel=33;
	  		Parameters.timeOut=100;//in secounds
	  		Parameters.trafficPattern="simple";;
	  		break;
	  	case 16:
	  		break;
	  	default:
	  		break;
	  }
  }
}



