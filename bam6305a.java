//// Elements & electrons -- bam6305a.java


Electron[] electron;
Element[] element;

String[] names=  { "(n)", "H", "He", "Li", "Be", "D", "C", "N", "O", "F", "Ne" 
  , "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar"
  , "K", "Ca"
};

int nE = 1;

float xAtom, yAtom;



void setup() {
  size (1000, 800);
  frameRate(150);
  
  xAtom=  width/2;
  yAtom=  height/2;

  smooth();
  noStroke();
  
  electron = new Electron[32];
  element = new Element[118];

  for (int z = 0; z < element.length; z++) {
    element[z] = new Element();
    element[z].z=  z;
    if (z<names.length) element[z].name = names[z];
  }     


  for (int i = 0; i < electron.length; i++) {
    electron[i] = new Electron();
  }
}

void draw() {
  background(0);
  fill( 255,255,0 );
  text( nE, width-50, 20 );
  text( "+ or - to change \natomic number Z", width-100, 40 );

/*--
  for (int i = 0; i < element.length; i++) {
    element[i].display();
  }                       //CREATE THE NUCLEUS
*/
    element[nE].display();

  for (int i = 0; i < nE; i++) {
    electron[i].display(); //DISPLAY THE ELECTRON
    electron[i].orbit(); //ORBIT THE ELECTRON
  }

}

void keyPressed() {
  if (key == '+') { nE++; }
  if (key == '-') { nE=  nE>0 ? nE-1 : 1; }
}

  
class Element {
  float eleX, eleY;
  float eleI;
  String name="?";
  
  int z;        // Atomic number.

  Element() {
    eleX = width/2;
    eleY = height/2;
    eleI = 25;
  }

  void display() {
    // Nucleus at eleX, eleY.
    fill(255);
    ellipseMode(RADIUS);
    ellipse(eleX, eleY, eleI, eleI);
    fill(0);
    textSize(25);
    // Name.  (Color-code?? //
    text( name, eleX-10, eleY+10);
    textSize( 10 );
    int xZ=15;                   // Subscript position
    if (z>10) xZ=20;
    if (z>100) xZ=25;            // Adjust for 1, 2, or 3 digits.
    text( z, eleX-xZ, eleY+15);
    electrons();
  }
  // Place electrons in proper orbitals.
  // This should be changed to fill octets, quantum numbers, etc.
  // (Construct a complete model; then populate it as Z increases.)
  void electrons() {
    // Electrons
    fill(255);
    float eX=eleX+50, eY=eleY;
    for ( int i=0; i<z; i++ ) {
      eX = eX + 20;
      fill(255);
      ellipse(eX, eY, 10, 10);
      fill(0);
      textSize(25);
      text("-", eX-7, eY+7);
      textSize(8);
      text(i+1, eX-5, eY+11);
    }
    fill( 255,0,0 );
    text( eX, width-50, 50 );
  }
}

class Electron {
  float ex, ey, vx, vy, nx, ny, ax, ay;

  Electron() {
    /*--
    ex = 200; //ELECTRON X & Y
    ey = 0;
    --*/
    ex=  20 + width/2;
    ey=  20 + height/2;

    vy = -1; //INCREASE VX & VY
    vx = 0;
  }

  void orbit() { 
    ax = -ex / sq(dist(ex, ey, 0, 0)); //FINDING DISTANCE BETWEEN BOTH X + Y COORDINATES
    ay = -ey / sq(dist(ex, ey, 0, 0));

    vx+=ax; //ADDING VX TO AX == 0 + AX equation
    vy+=ay; //ADDING VY TO AY == -1 + AX equation 

    ex+=vx; //ADDING EX TO VX == 200 + 0
    ey+=vy; //ADDING EY TO VY == 0 + 0
  }

  void display() {
    fill(255);
//--??    
translate(width/2.0, height/2.0);
    ellipse(ex, ey, 10, 10);
    fill(0);
    text("-", ex-7, ey+7);
  }
}
