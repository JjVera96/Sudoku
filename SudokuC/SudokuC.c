#include<stdio.h>
#include<cmath>

//Creamos una estructura llamada celda con un entero A y vector de enteros de los cuales seran los candidatos a poner en el entero A
struct celda{
	int entero;
	int candidatos[10];
};

//Una matriz de celdas la llamaremos Sudoku que sera sobre cual trabajaremos
struct celda Sudoku[9][9];

//Las siguientes tres funciones me verificaran si ya hay un numero en la columna, fila o cuadro
int VerificarColumna(int Num, int Columna){
	int i, Estado=1;
	for(i=0; i<9; i++){
		if(Num==Sudoku[i][Columna].entero){
			Estado=0;
			break;
		}
	}
	return Estado;
}

int VerificarFila(int Num, int Fila){
	int j, Estado=1;
	for(j=0; j<9; j++){
		if(Num==Sudoku[Fila][j].entero){
			Estado=0;
			break;
		}
	}
	return Estado;
}

int VerificarCuadro(int Num, int CeldaX, int CeldaY){
	int i, j, Estado=1;
	int iniCajaX, iniCajaY;
	iniCajaX=3*ceil(CeldaX/3);
	iniCajaY=3*ceil(CeldaY/3);
	for(i=iniCajaX; i< (3+iniCajaX); i++){
		for(j=iniCajaY; j<=(3+iniCajaY); j++){
			if(Num==Sudoku[i][j].entero){
				Estado=0;
				break;
			}
		}
	}
	return Estado;
}

//Funcion para leer desde teclado los numeros del Sudoku
void LlenarSudoku(){
	int i,j;
	printf("Ingrese 0 en donde no hay numeros\n");
	for(i=0; i<9; i++){
		for(j=0; j<9; j++){
			scanf("%d", &(Sudoku[i][j].entero));
			if(Sudoku[i][j].entero==0){
				Sudoku[i][j].candidatos[9]=0;
			}
			else{
				Sudoku[i][j].candidatos[9]=1;
			}
		}
	}
}

//Funcion para mostrar el Sudoku en pantalla ya bien sea resuelto o no 
void MostrarSudoku(int Forma){
	int i,j;
	printf("\n-------------------------------------------------------------------------\n");
	if(Forma==0){
		printf("Sudoku Leido\n");
	}
	else{
		printf("Sudoku Resuelto\n");
	}
	printf("-------------------------------------------------------------------------\n");
	for(i=0; i<9; i++){
		printf("|");
		for(j=0; j<9; j++){
			printf(" %d |", Sudoku[i][j].entero);
		}
		printf("\n");
	}
	printf("-------------------------------------------------------------------------\n");
}

//Funcion creada para verificar cuales Celdas se pueden cambiar o no en el proceso de averiguar los candidatos para cada celda
void MostrarSudokuCambiable(){
	int i,j;
	printf("-------------------------------------------------------------------------\n");
	for(i=0; i<9; i++){
		printf("|");
		for(j=0; j<9; j++){
			printf(" %d |", Sudoku[i][j].candidatos[9]);
		}
		printf("\n");
	}
	printf("-------------------------------------------------------------------------\n");
}

//Funcion que elimina los candidatos que no sirven del vector candidatos para cada Celda
void EliminarCandidatosCelda(int x, int y){
	int k, Cuadro;
	for(k=0; k<9; k++){
		if(Sudoku[x][y].candidatos[k]!=0){
			if(VerificarColumna(Sudoku[x][y].candidatos[k], y)==0){
				Sudoku[x][y].candidatos[k]=0;
			}
			if(VerificarFila(Sudoku[x][y].candidatos[k], x)==0){
				Sudoku[x][y].candidatos[k]=0;
			}
			if(VerificarCuadro(Sudoku[x][y].candidatos[k], x, y)==0){
				Sudoku[x][y].candidatos[k]=0;
			}
		}
	}
}

//Funcion que inicializa el sudoku desde 0 para empezar a resolverlo desde un principio 
void InicializarSudoku(){
	int i, j, k, n;
	for(i=0; i<9; i++){
		for(j=0; j<9; j++){
			if(Sudoku[i][j].candidatos[9]==0){
				for(k=0; k<9; k++){
					Sudoku[i][j].candidatos[k]=k+1;
				}
			}
		}
	}
	for(i=0; i<9; i++){
		for(j=0; j<9; j++){
			if(Sudoku[i][j].candidatos[9]==0){
				EliminarCandidatosCelda(i,j);
			}
		}
	}
}

void MostrarCandidatos(){
	int i, j, k;
	for(i=0; i<9; i++){
		for(j=0; j<9; j++){
			printf("(%d,%d)={", i, j);
			for(k=0; k<9; k++){
				printf("%d ", Sudoku[i][j].candidatos[k]);
			}
			printf("}\n");
		}
	}
}

//Funcion que me resuelve el sudoku
void SolvedSudoku(){
}

int main(){
	char c;
	do{
		LlenarSudoku();
		MostrarSudoku(0);
		printf("Presione 'y' para continuar 'n' para volver a leer el Sudoku\n");
		scanf("%s", &c);

	}while(c=='n');
	InicializarSudoku();
	SolvedSudoku();
	MostrarSudoku(1);
	return 0;
}