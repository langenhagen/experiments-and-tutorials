#include <iostream>
#include "Supermarkt.h"
using namespace std;

Product& isLowerCode( Product& a, Product& b);

void main()
{

	Product *arr[3];
	UnpackedFood *unptr = new UnpackedFood;

	arr[0] = new Product;
	arr[1] = new PackedFood;
	arr[2] = new UnpackedFood;

	unptr->printer();

	arr[0]->printer();
	((PackedFood*)arr[1])->printer();
	static_cast<UnpackedFood*>(arr[2])->printer();

	((Product*)unptr)->printer();

	arr[0]->scanner();
	arr[1]->scanner();
	arr[2]->scanner();

	isLowerCode( *arr[0], *arr[2]).printer();
}