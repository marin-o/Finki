#version 330 core
out vec4 FragColor;
in vec3 ourColr;



void main()
{
        FragColor = vec4(ourColr,1.0);
}
