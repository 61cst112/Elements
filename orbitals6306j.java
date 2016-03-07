//// Elements & electron orbitals -- elements6306j.java


Electron[] electron;
Element[] element;

String[] names=  { "(n)", "H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne" 
  , "Na", "Mg", "Al", "Si", "P ", "S ", "Cl", "Ar"
  , "K ", "Ca", "Sc", "Ti", "V ", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn"
        , "Ga", "Ge", "As", "Se", "Br", "Kr"
  , "Rb", "Sr", "Y ", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd"
        , "In", "Sn", "Sb", "Te", "I", "Xe"
  , "Cs", "Ba"
        , "La", "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb"
        , "Lu", "Hf", "Ta", "W ", "Re", "Os", "Ir", "Pt", "Au", "Hg"
        , "Tl", "Pb", "Bi", "Po", "At", "Xe"
  , "Fr", "Ra"
        , "Ac", "Th", "Pa", "U ", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm", "Md", "No"
        , "Lr", "Rf", "Db", "Sg", "Bh", "Hs", "Mt", "Ds", "Rg", "Cn"
        , "UUt", "Fl", "Uup", "Lv", "Uus", "Uuo"
};

int nE = 1;            // Atomic number
int maxE=  118;


float xAtom, yAtom;

int time=0;



void setup() {
  size (1000, 800);
  frameRate(150);
  
  xAtom=  width/2;
  yAtom=  height/2;

  smooth();
  noStroke();
  
  electron = new Electron[ maxE+61 ];
  element = new Element[ maxE+61 ];

  for (int z = 0; z < element.length; z++) {
    element[z] = new Element( z );
    if (z<names.length) element[z].name = names[z];
  }     

/*???
  for (int i = 0; i < electron.length; i++) {
    electron[i] = new Electron();
  }
???*/
}

void draw() {
  time++;
  background(0);
  fill( 255,255,0 );
  textSize(12);
  text( "Z="+nE, width-50, 20 );
  text( "+ or - to change atomic number Z", width-250, 40 );
  text( "Or letters such as H, C, N, O, F, K, U, etc.\n    A for silver, G for gold, ! for Uuo", width-250, 60 );

/*--
  for (int i = 0; i < element.length; i++) {
    element[i].display();
  }                       //CREATE THE NUCLEUS
*/
    element[nE].display();

/*???
  for (int i = 0; i < nE+1; i++) {
    electron[i].display(); //DISPLAY THE ELECTRON
    electron[i].orbit(); //ORBIT THE ELECTRON
  }
???*/

}

void keyPressed() {
  if (key == 'q') { exit(); }
  if (key == '0') { nE=0; }
  if (key == 'H') { nE=1; }
  if (key == 'B') { nE=5; }
  if (key == 'C') { nE=6; }
  if (key == 'N') { nE=7; }
  if (key == 'O') { nE=8; }
  if (key == 'F') { nE=9; }
  //
  if (key == 'P') { nE=15; }
  if (key == 'S') { nE=16; }
  if (key == 'K') { nE=19; }
  //
  if (key == 'A') { nE=47; }
  if (key == 'G') { nE=79; }
  if (key == 'U') { nE=92; }
  if (key == '!') { nE=maxE; }
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
    float theta;
    theta= TWO_PI * time * 0.0005;
    float radius;
    int ring=1;                                // n-quantum number (K,L,M,N, ...)
    float r=250, g=0, b=250;
    //
    for ( int i=1; i<z+1; i++ ) {
      if (i<=2) { ring=1; r=250; g=0; b=250; }               // K
      else if (i<=10) { ring=2; r=250; g=0; b=0; }           // L
      else if (i<=28) { ring=3; r=200; g=200; b=0; }         // M
      else if (i<=60) { ring=4; r=0; g=200; b=0; }           // M
      else if (i<=110) { ring=5; r=0; g=0; b=200; }          // N
      else { ring=6; r=0; g=200; b=200;  }           // O
      int pop=  2 * ring*ring;
      radius=  z/3 + 60 * ring;
      theta=  theta + TWO_PI/pop;
      //
      eX=  eleX + radius * sin( theta );
      eY=  eleY + radius * cos( theta );
      r += 1;
      g += 1;
      b += 1;
      fill(r,g,b);
      ellipse(eX, eY, 10, 10);
      fill(0);
      textSize(25);
      text("-", eX-7, eY+7);
      textSize(8);
      text(i+1, eX-7, eY+10);
    }
  }
}


