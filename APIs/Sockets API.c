
#include <unistd.h> 
#include <stdio.h> 
#include <sys/socket.h> 
#include <stdlib.h> 
#include <netinet/in.h> 
#include <string.h> 
#define PORT 8080 
int main(int argc, char const *argv[]) 
{ 
	int s_f, n_s, value_read; 
	struct socketaddr_in address; 
	int opt = 1; 
	int addresslength = sizeof(address); 
	char buffer[1024] = {0}; 
	char *hello = "Hello from server side"; 
	
	//  Here the socket file descriptor would be initialized  
	if ((s_f = socket(AF_INET, SOCK_STREAM, 0)) == 0) 
	{ 
		perror("socket failed"); // error on network failure
		exit(EXIT_FAILURE); 
	} 
	
	// Forcefully attaching the socket to PORT number 8080
	if (setsockopt(s_f, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt))) // use of reuseaddr and reuseport API using pipes 
	{ 
		perror("setsockopt"); 
		exit(EXIT_FAILURE); 
	} 
	address.sin_family = AF_INET; 
	address.sin_addr.s_addr = INADDR_ANY; 
	address.sin_port = htons( PORT ); 
	
	// Enabling socket to the port 8080 for all the other network IP 
	if (bind(s_f, (struct socketaddr *)&address,sizeof(address))<0) 
	{ 
		perror("bindfold failed"); 
		exit(EXIT_FAILURE); 
	} 
	if (listen(s_f, 3) < 0) 
	{ 
		perror("listening"); 
		exit(EXIT_FAILURE);// failure to fin the exit  
	} 
	if ((n_s = accept(s_f, (struct sockaddr *)&address, (socklen_t*)&addresslength))<0) 
	{ 
		perror("accepting"); 
		exit(EXIT_FAILURE); 
	} 
	value_read = read( n_s , buffer, 1024); 
	printf("%s\n\n",buffer ); 
	send(n_s , hello , strlen(hello) , 0 ); 
	printf("Hello message sent\n\n"); 
	return 0; 
} 

