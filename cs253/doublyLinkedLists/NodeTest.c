#include "Node.h"

int main()
{

	int obj1 = 10;
	struct node *head = createNode(&obj1);

	int obj2 = 20;
	struct node *tail = createNode(&obj2);

	head->next = tail;
    head->prev = NULL;
	
	tail->next = NULL;
	tail->prev = head;

	printf("head data: %d\n", *((int*)head->obj));
	printf("head->next data: %d\n", *((int*)head->next->obj));

}
