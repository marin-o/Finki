#include <glad/glad.h>
#include <GLFW/glfw3.h>
#include <vector>
#include <iostream>
#define _USE_MATH_DEFINES
#include <cmath>
void framebuffer_size_callback(GLFWwindow* window, int width, int height);
void processInput(GLFWwindow *window);

// settings
const unsigned int SCR_WIDTH = 800;
const unsigned int SCR_HEIGHT = 800;

const char *vertexShaderSource = "#version 330 core\n"
                                 "layout (location = 0) in vec3 aPos;\n"
                                 "void main()\n"
                                 "{\n"
                                 "   gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);\n"
                                 "}\0";
const char *fragmentShaderMainSource = "#version 330 core\n"
                                    "out vec4 FragColor;\n"
                                    "uniform vec4 u_Color;"
                                    "void main()\n"
                                    "{\n"
                                    "   FragColor = u_Color;\n"
                                    "}\n\0";

int main()
{
    // glfw: initialize and configure
    // ------------------------------
    glfwInit();
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

#ifdef __APPLE__
    glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE); // uncomment this statement to fix compilation on OS X
#endif

    // glfw window creation
    // --------------------
    GLFWwindow* window = glfwCreateWindow(SCR_WIDTH, SCR_HEIGHT, "LearnOpenGL", nullptr, nullptr);
    if (window == nullptr)
    {
        std::cout << "Failed to create GLFW window" << std::endl;
        glfwTerminate();
        return -1;
    }
    glfwMakeContextCurrent(window);
    glfwSetFramebufferSizeCallback(window, framebuffer_size_callback);

    // glad: load all OpenGL function pointers
    // ---------------------------------------
    if (!gladLoadGLLoader(GLADloadproc(glfwGetProcAddress)))
    {
        std::cout << "Failed to initialize GLAD" << std::endl;
        return -1;
    }


    // build and compile our shader program
    // ------------------------------------
    // vertex shader
    GLuint vertexShader = glCreateShader(GL_VERTEX_SHADER);

    glShaderSource(vertexShader, 1, &vertexShaderSource, nullptr);
    glCompileShader(vertexShader);
    // check for shader compile errors
    int success;
    char infoLog[512];
    glGetShaderiv(vertexShader, GL_COMPILE_STATUS, &success);
    if (!success)
    {
        glGetShaderInfoLog(vertexShader, 512, nullptr, infoLog);
        std::cout << "ERROR::SHADER::VERTEX::COMPILATION_FAILED\n" << infoLog << std::endl;
    }


    // link shaders
    GLuint fragmentShaderMain = glCreateShader(GL_FRAGMENT_SHADER);

    GLuint shaderProgram = glCreateProgram();

    glShaderSource(vertexShader,1,&vertexShaderSource,nullptr);
    glCompileShader(vertexShader);
    glShaderSource(fragmentShaderMain, 1, &fragmentShaderMainSource, nullptr);
    glCompileShader(fragmentShaderMain);


    glAttachShader(shaderProgram, vertexShader);
    glAttachShader(shaderProgram, fragmentShaderMain);
    glLinkProgram(shaderProgram);



    glGetShaderiv(fragmentShaderMain, GL_COMPILE_STATUS, &success);
    if(!success){
        glGetShaderInfoLog(vertexShader, 512, nullptr, infoLog);
        std::cout << "ERROR::SHADER::VERTEX::COMPILATION_FAILED\n" << infoLog << std::endl;
    }







    float vertices[] = {
            -0.65f,  0.45f, 0.0f,   // top left
            -0.65f, -0.45f, 0.0f,  // bottom left
            -0.40f,  0.45f, 0.0f,  // top right
            -0.40f, -0.45f, 0.0f  // bottom right
    };
    unsigned int indices[] = {  // note that we start from 0!
            0, 1, 2,  // first Triangle
            1, 2, 3   // second Triangle
    };
    unsigned int VBO, VAO, EBO;
    glGenVertexArrays(1, &VAO);
    glGenBuffers(1, &VBO);
    glGenBuffers(1, &EBO);
    // bind the Vertex Array Object first, then bind and set vertex buffer(s), and then configure vertex attributes(s).
    glBindVertexArray(VAO);

    glBindBuffer(GL_ARRAY_BUFFER, VBO);
    glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);

    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(indices), indices, GL_STATIC_DRAW);

    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(float), nullptr);
    glEnableVertexAttribArray(0);

    // note that this is allowed, the call to glVertexAttribPointer registered VBO as the vertex attribute's bound vertex buffer object so afterwards we can safely unbind
    glBindBuffer(GL_ARRAY_BUFFER, 0);




    std::vector<float> verticesRing,imagineDragonsLeft,imagineDragonsDown;

    float idAngle = 0.0f;
    float idRad1 = 0.4f;
    float idRad2 = 0.55f;
    float idAngles = 80;
    float idStep = 2*M_PI/(idAngles/2);
    int i;
    for(i=0;i<=idAngles;i++){
        float x = idRad1*cos(idAngle) + 0.2f;
        float y = idRad1*sin(idAngle);
            verticesRing.push_back(x);
            verticesRing.push_back(y);
            verticesRing.push_back(0.0f);
            x = idRad2*cos(idAngle) + 0.2f;
            y = idRad2*sin(idAngle);
            verticesRing.push_back(x);
            verticesRing.push_back(y);
            verticesRing.push_back(0.0f);
            idAngle+=idStep;
    }


    unsigned int VBOs, VAOs;
    glGenVertexArrays(1,&VAOs);
    glGenBuffers(1,&VBOs);
    //first segment
    glBindVertexArray(VAOs);

    glBindBuffer(GL_ARRAY_BUFFER,VBOs);
    glBufferData(GL_ARRAY_BUFFER, verticesRing.size() * sizeof(float), &verticesRing[0], GL_STATIC_DRAW);

    glVertexAttribPointer(0,3,GL_FLOAT,GL_FALSE,3*sizeof(float),nullptr);
    glEnableVertexAttribArray(0);
    glBindVertexArray(0);

    glUseProgram(shaderProgram);

    int uniformLocation = glGetUniformLocation(shaderProgram, "u_Color");
    while (!glfwWindowShouldClose(window))
    {
        // input
        // -----
        processInput(window);

        // render
        // ------
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        // draw our first triangle
        glUniform4f(uniformLocation,0.192f,0.192f,0.51f,1.0f);
        glBindVertexArray(VAO);
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT,0);
        glBindVertexArray(0);

        glUniform4f(uniformLocation,0.165f,0.576f,0.819f,1.0f);
        glBindVertexArray(VAOs);
        glDrawArrays(GL_TRIANGLE_STRIP,0,idAngles+2);
        glBindVertexArray(0);

        // glfw: swap buffers and poll IO events (keys pressed/released, mouse moved etc.)
        // -------------------------------------------------------------------------------
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    // optional: de-allocate all resources once they've outlived their purpose:
    // ------------------------------------------------------------------------
    glDeleteVertexArrays(1, &VAO);
    glDeleteBuffers(1, &VBO);

    // glfw: terminate, clearing all previously allocated GLFW resources.
    // ------------------------------------------------------------------
    glfwTerminate();
    return 0;
}

// process all input: query GLFW whether relevant keys are pressed/released this frame and react accordingly
// ---------------------------------------------------------------------------------------------------------
void processInput(GLFWwindow *window)
{
    if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS)
        glfwSetWindowShouldClose(window, true);
}

// glfw: whenever the window size changed (by OS or user resize) this callback function executes
// ---------------------------------------------------------------------------------------------
void framebuffer_size_callback(GLFWwindow* window, int width, int height)
{
    // make sure the viewport matches the new window dimensions; note that width and
    // height will be significantly larger than specified on retina displays.
    glViewport(0, 0, width, height);
}

