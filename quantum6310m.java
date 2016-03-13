//// Orbitals only

int ww=800, hh=800;
float xA=ww/2, yA=hh/2;          // Position of nucleus.
float rE=80, theta=  PI / 4;     // Position of electron.
float wN=50, wE=20;              // Width of nucleus & electron.

int nE=  200;                    // Number of electrons.
int nEshow=  17;
Electron[] e=  new Electron[nE];

int n=1, l=1, m=1, s=0;         // Quantum numbers.
int a=0;                        // Atomic number.

float rotate=0, rotation=0;          // Press 'r' key for 30-second rotation;


float xLog=10, yLog=50;        // Log of electrons added (for debugging).
int bug=0;

String[] names= { 
  "(n)", "H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne" 
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
int bg=0;        // Background color.  (# to change.)
int qSize=6;

//// Setup:  construct the array.  ////
void setup() {
  size( ww, hh );
  setupElectrons();
}
void setupElectrons() {
  String log="";
  int ring;
  a=0;                  // neutron (placeholder at zero)
  e[a]=  new Electron( 0, 0, m=0, 0 );          
  e[a].setcolor( 255, 255, 255 );
  e[a].print();

  //--  p=  a +": "+ e[a].q +"  "+ e[a].radius +" "+ e[a].theta +" "+ e[a].dtheta;                //List the quantum numbers)
  //--  println( p );
  
  //List the quantum numbers)
  println( "Create electrons using quantum numbers." );
  // First two electrons (in K shell).
  // H, He
  n=1;
  l=0;
  a=1;
  ring=2;
  e[a]=  new Electron( n, l, m=1, 0 );
  e[a].setcolor( 255, 0, 0 );
  e[a].print();
  // He.
  a=2;
  e[a]=  new Electron( n, l, m=2, 1 );
  e[a].setcolor( 255, 0, 0 );
  e[a].print();
  println( "theta: "+ e[a].theta );
  println( "dtheta: "+ e[a].dtheta );
  println( "----" );
  // Next 8:  Li, Be, B, C, ...
  n=2;
  l=0;
  m=1;
  ring=8;
  for ( int i=0; i<ring; i++ ) {
    a=  3 + i;
    if (i==2) { 
      l=1; 
      m=1;
    }
    e[a]=  new Electron( n, l, m, s );
    e[a].setcolor( 255, 127, 0 );
    e[a].print();
    //--  println( a +": "+ e[a].q +"  "+ e[a].radius +" "+ e[a].theta +" "+ e[a].dtheta );                //List the quantum numbers)
    // Next.
    m++;
    s=  a%2 >0 ? 1 : 0;
    String as=  "For a=" + a + ", s=" + s;
    println( as );
  }
  println( "----" );
  // Next 18:  K, Ca, ...
  n=3;
  l=0;
  m=1;
  ring=18;
  for ( int i=0; i<ring; i++ ) {
    a++;
    if (i==2) { 
      l=1; 
      m=1;
    }
    if (i==8) { 
      l=2; 
      m=1;
    }
    e[a]=  new Electron( n, l, m, s );
    e[a].setcolor( 255, 255, 0 );
    //List the quantum numbers)
    e[a].print();
    //--  println( a +": "+ e[a].q +"  "+ e[a].radius +" "+ e[a].theta +" "+ e[a].dtheta );                //List the quantum numbers)
    // Next.
    m++;
    s=  a%2 >0 ? 1 : 0;
  }
  n=4;
  l=0;
  m=1;
  ring=32;
  for ( int i=0; i<ring; i++ ) {
    a++;
    if (i==2) { 
      l=1; 
      m=1;
    }
    if (i==10) { 
      l=2; 
      m=1;
    }
    e[a]=  new Electron( n, l, m, s );
    e[a].setcolor( 100, 200, 0 );
    //List the quantum numbers)
    e[a].print();
    //--  println( a +": "+ e[a].q +"  "+ e[a].radius +" "+ e[a].theta +" "+ e[a].dtheta );                //List the quantum numbers)
    // Next.
    m++;
    s=  a%2 >0 ? 0 : 1;
  }
  n=5;
  l=0;
  m=1;
  for ( int i=0; i<64; i++ ) {
    a++;
    if (i==2) { 
      l=1; 
      m=1;
    }
    if (i==10) { 
      l=2; 
      m=1;
    }
    if (i==28) { 
      l=3; 
      m=1;
    }
    e[a]=  new Electron( n, l, m, s );
    e[a].setcolor( 0, 1000, 200 );
    e[a].print();
    //List the quantum numbers)
    //--  println( a +": "+ e[a].q +"  "+ e[a].radius +" "+ e[a].theta +" "+ e[a].dtheta );                //List the quantum numbers)
    // Next.
    m++;
    s=  a%2 >0 ? 0 : 1;
  }
  n=6;
  l=0;
  m=1;
  for ( int i=0; 99+i<nE; i++ ) {
    a++;
    if (a>nE-1) break;
    if (i==2) { 
      l=1; 
      m=1;
    }
    if (i==10) { 
      l=2; 
      m=1;
    }
    if (i==28) { 
      l=3; 
      m=1;
    }
    e[a]=  new Electron( n, l, m, s );
    e[a].setcolor( 0, 100, 200 );
    //List the quantum numbers)
    e[a].print();
    //--  println( a +": "+ e[a].q +"  "+ e[a].radius +" "+ e[a].theta +" "+ e[a].dtheta );                //List the quantum numbers)
    // Next.
    m++;
    s=  a%2 >0 ? 0 : 1;
  }
}



//// Display the nucleus ////
void draw() {
  background(bg);
  fill( 255 );

  textSize(20);  
  text( "Quantum numbers for electrons.", width/3, 20 );
  textSize(12);  
  text( "+ or - to change element number (or use H,C,O,N,F, .., W,U,X,!, etc.)", width/3, 40 );
  text( "    (For rotation, press '>' or '< key, or '|' to slow down.)   "+rotation, width/3, 60 );
  text( "Debugging codes:  @ # ^ etc.", width*2/3, height-30 );
  text( "0 or 1 to for background.", width*2/3, height-20 );

  if (nEshow<1) nEshow=1;
  if (nEshow>=nE) nEshow=nE-1;
  int z=  nEshow-1;
  wN = 20  +  0.5 * z;        // Width of nucleus.

  ellipse( xA, yA, wN, wN );        // Nucleus at center
  fill(0);
  text( "a="+(nEshow-1), xA-20, yA );

  xLog=10;  
  yLog=10;

  rotate += rotation;        // 10-second orbit

  //// Display the nucleus.
  nucleus( nEshow-1 );


  //// Display the electrons. ////
  electrons( nEshow-1);
}
void electrons( int a ) {
  for (int i=1; i<a; i++ ) {
    e[i].show();
    // DEBUG: show quantum numbers.
    String name="??";
    if (i<names.length) name=names[i];
    text( i +" "+ name, xLog, yLog );    // Log (list the quantum numbers)
    text( e[i].q, xLog+40, yLog );          // Log (list the quantum numbers)
    //--  text( "r="+int(e[i].radius), xLog+50, yLog );    // Log (list the quantum numbers)
    text( e[i].theta/TWO_PI, xLog+80, yLog );    // Log (list the quantum numbers)
    yLog += 12;
    if (yLog> height-20) { 
      yLog=15; 
      xLog+=120;
    }
  }
  // DEBUG:  display last electron data.
  if (bug>0) {
    int i=a-1;
    e[i].showBIG();
  }
  /*
  textSize(20);
  fill(255,0,0);
  String name="??";
    if (i<names.length) name=names[i];
    text( i +" "+ name, width-200, 100 );    // Log (list the quantum numbers)
    text( e[i].q, width-150, 100 );          // Log (list the quantum numbers)
    //--  text( "r="+int(e[i].radius), xLog+50, yLog );    // Log (list the quantum numbers)
    text( e[i].theta/TWO_PI, width-100, 100 );    // Log (list the quantum numbers)
  */
}
void nucleus( int a ) {
  // Nucleus at xA, yA.
  fill(255);
  ellipseMode(RADIUS);
  ellipse(xA, yA, wN, wN);
  fill(0);
  textSize(25);
  // Name.  (Color-code?? //
  if (a<names.length) text( names[a], xA-10, yA+10);
  textSize( 10 );
  int xZ=15;                   // Subscript position
  if (a>10) xZ=20;
  if (a>100) xZ=25;            // Adjust for 1, 2, or 3 digits.
  text( a, xA-xZ, yA+15);
  // Atomic mass.
  textSize( 8 );
  //--  int amu=  nE + nN;
  int neutrons=  0;
  if (a>1) neutrons=a;
  if (a>20) neutrons=  int( 1.2 * a);        // ++++ GET A BETTER APPROXIMATION!
  if (a>40) neutrons=  int( 1.3 * a);
  if (a>60) neutrons=  int( 1.4 * a);
  if (a>80) neutrons=  int( 1.6 * a);
  int amu=  a + neutrons;
  if (amu>10) xZ=20;
  if (amu>100) xZ=25;           // Adjust for 1, 2, or 3 digits.
  text( amu, xA-xZ, yA-5);
  //--  text( neutrons, xA-xZ, yA-15);
}




void keyPressed() {
  if (key == 'q') exit();
  nEshow=  constrain( nEshow, 1, nE-1 );
  if (key == '>') { 
    rotation *= 1.25; 
    if (rotation<=0) rotation = TWO_PI / 900;
  }
  if (key == '<') { 
    rotation *= 1.25; 
    if (rotation>=0) rotation = -TWO_PI / 900;
  }
  if (key == '|') { 
    rotation *= 0.8; 
    if (abs(rotation) < 0.001) rotation=0;
  }
  // Selected elements
  int a=  nEshow-1;
  if (key == 'H') a=1;
  if (key == 'C') a=6;
  if (key == 'N') a=7;
  if (key == 'O') a=8;
  if (key == 'F') a=9;
  if (key == 'P') a=15;
  if (key == 'S') a=16;
  if (key == 'K') a=19;
  if (key == '*') a=26;
  if (key == 'Z') a=30;
  if (key == 'Y') a=39;
  //
  if (key == 'A') a=47;
  if (key == 'I') a=53;
  if (key == 'W') a=74;
  if (key == 'G') a=79;
  if (key == 'U') a=92;
  if (key == 'X') a=112;
  if (key == '!') a=nE-3;
  nEshow=  a+1;
  if (key == '+') nEshow++;
  if (key == '-') nEshow--;
  if (key == '0') bg = 0;
  if (key == '1') bg=255;
  if (key == '^') { qSize++;  if (qSize>18) qSize=  4; }
  if (key == '@') { qSize=18; bg=255; }
  if (key == '#') { bug++; if (bug>9) bug=0; }
}

//// Electron:  place each electron in proper position, based on quantum numbers.
class Electron {
  float radius=400, theta=PI/4;        // radius & angle.
  float dtheta=  PI;
  float stheta=  PI/32;                // Pair-spacing
  float x, y;
  int r=255, g=266, b=255;

  int j=0;                        // Electron number
  int n=1, l=0, m=0, s=0;
  String lName[]= { 
    "s", "p", "d", "f", "g", "h"
  };
  String spinName[]= { 
    "-", "+"
  };
  // Maximum e per n.
  int maxE[]= { 
    2, 8, 18, 32, 50
  };

  String q="QQQ";


  //// CONSTRUCTORS ////
  Electron( int j ) {
    this.j=  j;
  }
  Electron( int n, int l, int m, int s ) {
    this.n=  n;
    this.l=  l;
    this.m=  m;
    this.s=  s;
    // Set the q string.
    this.q= n + lName[l] + m;
    this.q += s<1? "-" : "+";
    // Radius
    radius =  50 * n;

    println( "@:  "+ n + l + m + spinName[s] );

    // Set theta & dtheta
    int mm=  int( (m-1) / 2 );        // Electron pairs
    // s:  m=1..2
    if (l==0) {
      if (n==1) {
        stheta=  PI;
        dtheta=  stheta;
        theta = -PI/4;                // Inner pair at 45.
        theta += s>0 ? PI : 0;
      } else if (n==4) {
        stheta=  TWO_PI;
        dtheta=  stheta;          //??????????++
        theta = +PI/4;                // Inner pair at 45.
        if (m>1) theta = PI-PI/4;                // Inner pair at 45.
        theta += s>0 ? PI : 0;
      } else {
        stheta=  TWO_PI / 32;      //????
        dtheta=  stheta;
        theta= stheta * mm;
        theta += s>0 ? stheta : -stheta;
      }
    }
    // p:  m=1..6
    if (l==1) {
      dtheta= TWO_PI / 4;
      theta= TWO_PI/4   +   dtheta * mm;
      theta += s>0 ? stheta : -stheta;
    }
    // d:  m=1..10
    if (l==2) {
      if (m==1) { theta = -TWO_PI/8; }
      else if (m==2) { theta = PI - TWO_PI/8; }
      else if (m<11) {
        theta = +TWO_PI/8;
        dtheta=  TWO_PI / 4;
        stheta=  TWO_PI / 32;      //????
        theta += dtheta * mm;
        theta += s>0 ? stheta : -stheta;
      } else {
        theta = -TWO_PI/16;        //?????
        dtheta=  TWO_PI / 8;
        stheta=  TWO_PI / 32;      //????
        theta += dtheta * mm;
        theta += s>0 ? stheta : -stheta;
      }
    }
    //+++++++++++++++++++++ STILL NEEDS WORK, HERE.  ++++++++++
    // f:  m=1..14
    if (l==3) {
        theta=  PI/16;
        dtheta=  TWO_PI / 16;
        stheta=  TWO_PI / 32;      //????
        theta += dtheta * mm;
        theta += s>0 ? stheta : -stheta;
    }
    if (l>3 && l<10) {
      dtheta=  TWO_PI / 50;
      theta=0 + dtheta * mm;
      stheta=  TWO_PI / 64;      //????
      theta += s>0 ? stheta : -stheta;
    }
    if (l>10) {
      dtheta=  TWO_PI / 50;
      theta=0 + dtheta * mm;
      stheta=  TWO_PI / 64;      //????
      theta += s>0 ? stheta : -stheta;
    }
  }
  void setcolor( int r, int g, int b ) {
    this.r=r;
    this.g=g;
    this.b=b;
  }
  void print() {
    String name="??";
    if (a<names.length) name=names[a];
    String p=  a +" "+ name +": "+ q +"  "+ radius +" "+ (theta)/PI +" "+ (dtheta)/PI ;
    //List the quantum numbers)
    println( p );
  }


  //// METHODS ////
  // Display the electron at proper position.
  void show() {
    String comma=",";
    //--  String q= n +comma+ subName[l] +comma+ m +comma;
    q= n + lName[l] +"."+ m;
    q += s>0? "+" : "-";
    // Now, calculate radius, theta
    float rE=  wN - 30 + radius;
    x=  xA + rE*cos(theta+rotate);
    y=  yA + rE*sin(theta+rotate);
    fill(r, g, b);
    ellipseMode(CENTER);
    ellipse( x, y, wE, wE );
    //
    textSize(6);
    text( q, x-10, y+20 );
    //--  text( theta/TWO_PI, x-10, y+30 );
    //--  text( ""+n+comma+l+comma+m+comma+s, x-10, y+40 );
    fill(0);
    textSize(qSize);
    text( q, x-9, y+3 );
    text(theta/TWO_PI, x-9, y+18 );
    textSize(12);
    fill(r, g, b);
  }
  // DEBUG:  Show electon data.
  void showBIG() {
    String comma=",";
    //--  String q= n +comma+ subName[l] +comma+ m +comma;
    q= n + lName[l] +"."+ m;
    q += s>0? "+" : "-";
    // Now, calculate radius, theta
    float rE=  wN - 30 + radius;
    x=  xA + rE*cos(theta+rotate);
    y=  yA + rE*sin(theta+rotate);
    fill(r, g, b);
//--    ellipseMode(CENTER);
//--    ellipse( x, y, wE, wE );
    //
    float yy=y+20;
    textSize(16);
    text( q, x-10, yy+20 );
    //--  text( theta/TWO_PI, x-10, y+30 );
    //--  text( ""+n+comma+l+comma+m+comma+s, x-10, y+40 );
    //--  fill(0);
    //--  textSize(qSize);
    //--  text( q, x-9, yy+30 );
    float pct=theta/TWO_PI;
    pct=  int(1000*pct) / 10.0;
    text( pct+"%", x-9, yy+40 );
    textSize(12);
    fill(r, g, b);
  }
  
}
