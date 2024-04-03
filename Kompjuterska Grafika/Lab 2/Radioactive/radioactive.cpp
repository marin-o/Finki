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

static const char *vertexShaderSource = "#version 330 core\n"
    "layout (location = 0) in vec3 aPos;\n"
    "void main()\n"
    "{\n"
    "   gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);\n"
    "}\0";
static const char *fragmentShaderSource = "#version 330 core\n"
    "out vec4 FragColor;\n"
    "void main()\n"
    "{\n"
    "   FragColor = vec4(0.0f, 0.0f, 0.0f, 1.0f);\n"
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
    // fragment shader
    GLuint fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
    glShaderSource(fragmentShader, 1, &fragmentShaderSource, nullptr);
    glCompileShader(fragmentShader);
    // check for shader compile errors
    glGetShaderiv(fragmentShader, GL_COMPILE_STATUS, &success);
    if (!success)
    {
        glGetShaderInfoLog(fragmentShader, 512, nullptr, infoLog);
        std::cout << "ERROR::SHADER::FRAGMENT::COMPILATION_FAILED\n" << infoLog << std::endl;
    }
    // link shaders
    GLuint shaderProgram = glCreateProgram();
    glAttachShader(shaderProgram, vertexShader);
    glAttachShader(shaderProgram, fragmentShader);
    glLinkProgram(shaderProgram);
    // check for linking errors
    glGetProgramiv(shaderProgram, GL_LINK_STATUS, &success);
    if (!success) {
        glGetProgramInfoLog(shaderProgram, 512, nullptr, infoLog);
        std::cout << "ERROR::SHADER::PROGRAM::LINKING_FAILED\n" << infoLog << std::endl;
    }
    glDeleteShader(vertexShader);
    glDeleteShader(fragmentShader);

    // set up vertex data (and buffer(s)) and configure vertex attributes
    // ------------------------------------------------------------------
    /*float vertices_a[] = {
        -0.5f, -0.5f, 0.0f, // left
         0.5f, -0.5f, 0.0f, // right
         0.0f,  0.5f, 0.0f  // top
    };*/

    std::vector<float> vertices;

    float angle = 0.0f;
    float radius = 0.15f;
    int angles=40;
    float angleStep=2*M_PI/angles;

    for (int i=0;i<=angles;i++) {
        float x = radius*cos(angle);
        float y = radius*sin(angle);
        vertices.push_back(x);
        vertices.push_back(y);
        vertices.push_back(0.0f);

        angle+=angleStep;
    }





    unsigned int VBO, VAO;
    glGenVertexArrays(1, &VAO);
    glGenBuffers(1, &VBO);
    // bind the Vertex Array Object first, then bind and set vertex buffer(s), and then configure vertex attributes(s).
    glBindVertexArray(VAO);

    glBindBuffer(GL_ARRAY_BUFFER, VBO);
    glBufferData(GL_ARRAY_BUFFER, vertices.size() * sizeof(float), &vertices[0], GL_STATIC_DRAW);
    //glBufferData(GL_ARRAY_BUFFER, sizeof(vertices_a), vertices_a, GL_STATIC_DRAW);



    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(float), nullptr);
    glEnableVertexAttribArray(0);

    // note that this is allowed, the call to glVertexAttribPointer registered VBO as the vertex attribute's bound vertex buffer object so afterwards we can safely unbind
    glBindBuffer(GL_ARRAY_BUFFER, 0);

    // You can unbind the VAO afterwards so other VAO calls won't accidentally modify this VAO, but this rarely happens. Modifying other
    // VAOs requires a call to glBindVertexArray anyways so we generally don't unbind VAOs (nor VBOs) when it's not directly necessary.
    glBindVertexArray(0);


    std::vector<float> imagineDragonsRight,imagineDragonsLeft,imagineDragonsDown;

    float idAngle = 0.0f;
    float idRad1 = 0.225f;
    float idRad2 = 0.75f;
    float idAngles = 80;
    float idStep = 2*M_PI/(idAngles/2);
    int i;
    for(i=0;i<=idAngles;i++){
        float x = idRad1*cos(idAngle);
        float y = idRad1*sin(idAngle);
        if(idAngle<1.25){
            imagineDragonsRight.push_back(x);
            imagineDragonsRight.push_back(y);
            imagineDragonsRight.push_back(0.0f);
            x = idRad2*cos(idAngle);
            y = idRad2*sin(idAngle);
            imagineDragonsRight.push_back(x);
            imagineDragonsRight.push_back(y);
            imagineDragonsRight.push_back(0.0f);
            idAngle+=idStep;
        }
        else if(idAngle>=1.25 && idAngle<=2.0){
            idAngle+=idStep;
        }
        else if(idAngle>2.0 && idAngle<3.25){
            //crtame
            imagineDragonsLeft.push_back(x);
            imagineDragonsLeft.push_back(y);
            imagineDragonsLeft.push_back(0.0f);
            x = idRad2*cos(idAngle);
            y = idRad2*sin(idAngle);
            imagineDragonsLeft.push_back(x);
            imagineDragonsLeft.push_back(y);
            imagineDragonsLeft.push_back(0.0f);
            idAngle+=idStep;
        }
        else if(idAngle>=3.25 && idAngle <= 4.1){
            idAngle+=idStep;
        }
        else if(idAngle>4.1 && idAngle<=5.25){
            //crtame
            imagineDragonsDown.push_back(x);
            imagineDragonsDown.push_back(y);
            imagineDragonsDown.push_back(0.0f);
            x = idRad2*cos(idAngle);
            y = idRad2*sin(idAngle);
            imagineDragonsDown.push_back(x);
            imagineDragonsDown.push_back(y);
            imagineDragonsDown.push_back(0.0f);
            idAngle+=idStep;
        }

//        std::cout<<idAngle<<std::endl;
    }


    unsigned int VBOs[3], VAOs[3];
    glGenVertexArrays(3,VAOs);
    glGenBuffers(3,VBOs);
    //first segment
    glBindVertexArray(VAOs[0]);

    glBindBuffer(GL_ARRAY_BUFFER,VBOs[0]);
    glBufferData(GL_ARRAY_BUFFER,imagineDragonsRight.size()*sizeof(float),&imagineDragonsRight[0],GL_STATIC_DRAW);

    glVertexAttribPointer(0,3,GL_FLOAT,GL_FALSE,3*sizeof(float),nullptr);
    glEnableVertexAttribArray(0);
    glBindVertexArray(0);
    //2nd segment
    glBindVertexArray(VAOs[1]);

    glBindBuffer(GL_ARRAY_BUFFER,VBOs[1]);
    glBufferData(GL_ARRAY_BUFFER,imagineDragonsLeft.size()*sizeof(float),&imagineDragonsLeft[0],GL_STATIC_DRAW);

    glVertexAttribPointer(0,3,GL_FLOAT,GL_FALSE,3*sizeof(float),nullptr);
    glEnableVertexAttribArray(0);
    glBindVertexArray(0);
    //2nd segment
    glBindVertexArray(VAOs[2]);

    glBindBuffer(GL_ARRAY_BUFFER,VBOs[2]);
    glBufferData(GL_ARRAY_BUFFER,imagineDragonsDown.size()*sizeof(float),&imagineDragonsDown[0],GL_STATIC_DRAW);

    glVertexAttribPointer(0,3,GL_FLOAT,GL_FALSE,3*sizeof(float),nullptr);
    glEnableVertexAttribArray(0);
    glBindVertexArray(0);

    // uncomment this call to draw in wireframe polygons.
    //glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

    // render loop
    // -----------
    while (!glfwWindowShouldClose(window))
    {
        // input
        // -----
        processInput(window);

        // render
        // ------
        glClearColor(0.7f, 0.5f, 0.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        // draw our first triangle
        glUseProgram(shaderProgram);
        glBindVertexArray(VAO);
        glDrawArrays(GL_TRIANGLE_FAN, 0, angles+2);
        glBindVertexArray(0);

        glBindVertexArray(VAOs[0]);
        glDrawArrays(GL_TRIANGLE_STRIP,0,idAngles/3+2);
        glBindVertexArray(0);

        glBindVertexArray(VAOs[1]);
        glDrawArrays(GL_TRIANGLE_STRIP,0,idAngles/3+2);
        glBindVertexArray(0);

        glBindVertexArray(VAOs[2]);
        glDrawArrays(GL_TRIANGLE_STRIP,0,idAngles/3+2);
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

