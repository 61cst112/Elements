//// Elements & electrons -- bam6306b.java


Electron[] electron;
Element[] element;

String[] names=  { "(n)", "H", "He", "Li", "Be", "D", "C", "N", "O", "F", "Ne" 
  , "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar"
  , "K", "Ca"
};

int nE = 1;            // Atomic number
int maxE=  118;


float xAtom, yAtom;



void setup() {
  size (1000, 800);
  frameRate(150);
  
  xAtom=  width/2;
  yAtom=  height/2;

  smooth();
  noStroke();
  
  electron = new Electron[ maxE+1 ];
  element = new Element[ maxE+1 ];

  for (int z = 0; z < element.length; z++) {
    element[z] = new Element( z );
    if (z<names.length) element[z].name = names[z];
  }     


  for (int i = 0; i < electron.length; i++) {
    electron[i] = new Electron();
  }
}

void draw() {
  background(0);
  fill( 255,255,0 );
  textSize(12);
  text( nE, width-50, 20 );
  text( "+ or - to change \natomic number Z", width-100, 40 );

/*--
  for (int i = 0; i < element.length; i++) {
    element[i].display();
  }                       //CREATE THE NUCLEUS
*/
    element[nE].display();

  for (int i = 0; i < nE+1; i++) {
    electron[i].display(); //DISPLAY THE ELECTRON
    electron[i].orbit(); //ORBIT THE ELECTRON
  }

}

void keyPressed() {
  if (key == '0') { nE=0; }
  if (key == 'H') { nE=1; }
  if (key == 'C') { nE=12; }
  if (key == 'A') { nE=79; }
  if (key == 'U') { nE=92; }
  //
  if (key == '+') { nE++; }
  if (key == '-') { nE--; }
  nE=  constrain( nE, 0, element.length-1 ); 
  //
  if (key == 'n') { element[nE].nN++; }
  if (key == 'N') { element[nE].nN--; }
}

  
class Element {
  float eleX=width/2, eleY=height/2;
  float eleI;           // Element diameter.
  String name="?";
  
  int z;                // Atomic number.
  int nN;               // Number of neutrons

  Element() {
    eleX = width/2;
    eleY = height/2;
    eleI = 25;
  }
  Element( int z ) {
    this.z=  z;
    nN=  z;
    if (z == 1) nN=  0;
    if (z == 0) nN=  1;
    if (z > 10) nN=  int( z * 1.25 );
    if (z > 50) nN=  int( z * 1.35 );
    if (z > 80) nN=  int( z * 1.55 );
    eleI = 20  +  0.5 * z;
  }
  Element( float x, float y ) {
    eleX = x;
    eleY = y;
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
    // Atomic mass.
    textSize( 8 );
    int amu=  nE + nN;
    if (amu>10) xZ=20;
    if (amu>100) xZ=25;           // Adjust for 1, 2, or 3 digits.
    text( amu, eleX-xZ, eleY-5);
    // Electrons.
    electrons();
  }
  // Place electrons in proper orbitals.
  // This should be changed to fill octets, quantum numbers, etc.
  // (Construct a complete model; then populate it as Z increases.)
  void electrons() {
    // Electrons
    fill(255);
    float eX, eY;
    eX=  eleX + 30 + 0.5*z;
    eY=  eleY;
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
