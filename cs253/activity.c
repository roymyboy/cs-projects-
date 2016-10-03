#include <stdio.h>

int main(char **argv)
{
		if(strcmp(argv[1], "word") != 0)
		{
		printf("Usage: activity <word> {more words.....}");
		exit(1);
		}

		char *word = argv[1];
		char *wordCopy = (char *) maloc(sizeof(char)*(*strlen(word)+1));
		printf("original: %s\n", word);
		printf("original: %s\n", wordCopy);
		free(wordCopy);
}


void mystrycpy(char *source, char *target)
{
	while(())	
}
