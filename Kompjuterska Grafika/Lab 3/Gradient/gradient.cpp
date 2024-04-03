#include <OpenGLPrj.hpp>

#include <GLFW/glfw3.h>

#include <iostream>

#include <vector>



const std::string program_name = ("GLSL fragment shader stepgradient");

void framebuffer_size_callback(GLFWwindow* window, int width, int height);
void processInput(GLFWwindow *window);

// settings
const unsigned int SCR_WIDTH = 800;
const unsigned int SCR_HEIGHT = 600;

static const char *vertexShaderSource ="#version 330 core\n"
                                       "layout (location = 0) in vec3 aPos;\n"
                                       "layout (location = 1) in vec3 aColor;\n"
                                       "flat out vec3 ourColor;\n"
                                       "void main()\n"
                                       "{\n"
                                       "gl_Position = vec4(aPos, 1.0);\n"
                                       "ourColor = aColor;\n"
                                       "}\0";

static const char *fragmentShaderSource = "#version 330 core\n"
                                          "flat in vec3 ourColor;\n"
                                          "out vec4 FragColor;\n"
                                          "void main()\n"
                                          "{\n"
                                          "    FragColor = vec4(ourColor, 1.0f);\n"
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
    GLFWwindow* window = glfwCreateWindow(SCR_WIDTH, SCR_HEIGHT, program_name.c_str(), nullptr, nullptr);
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
    if (!gladLoadGLLoader(reinterpret_cast<GLADloadproc>(glfwGetProcAddress)))
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
    glBindAttribLocation(shaderProgram, 0, "aPos");
    glBindAttribLocation(shaderProgram, 1, "aColor");

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

    std::vector<float> blue;

    unsigned int indices[] = {
            0,1,2,
            1,2,3, //1
            4,5,6,
            5,6,7, //2
            8,9,10,
            9,10,11, //3
            12,13,14,
            13,14,15, //4
            16,17,18,
            17,18,19, //5
            20,21,22,
            21,22,23, //6
            24,25,26,
            25,26,27, //7
            28,29,30,
            29,30,31, //8
            32,33,34,
            33,34,35, //9
            36,37,38,
            37,38,39, //10
    };

    float leftX = -0.75f;
    float rightX = -0.60;

    float highY,lowY;
    highY = 0.75f;
    lowY = 0.5f;

    float blueShade = 0.0f;
    for(int i = 0;i < 10; i++){
        //top left
        blue.push_back(leftX);
        blue.push_back(highY);
        blue.push_back(0.0f);
        blue.push_back(0.0f);
        blue.push_back(0.0f);
        blue.push_back(blueShade);
        //top right
        blue.push_back(rightX);
        blue.push_back(highY);
        blue.push_back(0.0f);
        blue.push_back(0.0f);
        blue.push_back(0.0f);
        blue.push_back(blueShade);
        //bot left
        blue.push_back(leftX);
        blue.push_back(lowY);
        blue.push_back(0.0f);
        blue.push_back(0.0f);
        blue.push_back(0.0f);
        blue.push_back(blueShade);
        //bot right
        blue.push_back(rightX);
        blue.push_back(lowY);
        blue.push_back(0.0f);
        blue.push_back(0.0f);
        blue.push_back(0.0f);
        blue.push_back(blueShade);

        blueShade+=0.1f;
        leftX = rightX;
        rightX += 0.15f;
    }

    std::vector<float> green;

    leftX = -0.75f;
    rightX = -0.60;

    highY =0.25f;
    lowY = 0.0f;

    float greenShade = 0.0f;
    for(int i = 0;i < 10; i++){
        //top left
        green.push_back(leftX);
        green.push_back(highY);
        green.push_back(0.0f);
        green.push_back(0.0f);
        green.push_back(greenShade);
        green.push_back(0.0f);
        //top right
        green.push_back(rightX);
        green.push_back(highY);
        green.push_back(0.0f);
        green.push_back(0.0f);
        green.push_back(greenShade);
        green.push_back(0.0f);
        //bot left
        green.push_back(leftX);
        green.push_back(lowY);
        green.push_back(0.0f);
        green.push_back(0.0f);
        green.push_back(greenShade);
        green.push_back(0.0f);
        //bot right
        green.push_back(rightX);
        green.push_back(lowY);
        green.push_back(0.0f);
        green.push_back(0.0f);
        green.push_back(greenShade);
        green.push_back(0.0f);

        greenShade+=0.1f;
        leftX = rightX;
        rightX += 0.15f;
    }

    std::vector<float> red;

    leftX = -0.75f;
    rightX = -0.60;

    highY =-0.25f;
    lowY = -0.5f;

    float redShade = 0.0f;
    for(int i = 0;i < 10; i++){
        //top left
        red.push_back(leftX);
        red.push_back(highY);
        red.push_back(0.0f);
        red.push_back(redShade);
        red.push_back(0.0f);
        red.push_back(0.0f);
        //top right
        red.push_back(rightX);
        red.push_back(highY);
        red.push_back(0.0f);
        red.push_back(redShade);
        red.push_back(0.0f);
        red.push_back(0.0f);
        //bot left
        red.push_back(leftX);
        red.push_back(lowY);
        red.push_back(0.0f);
        red.push_back(redShade);
        red.push_back(0.0f);
        red.push_back(0.0f);
        //bot right
        red.push_back(rightX);
        red.push_back(lowY);
        red.push_back(0.0f);
        red.push_back(redShade);
        red.push_back(0.0f);
        red.push_back(0.0f);

        redShade+=0.1f;
        leftX = rightX;
        rightX += 0.15f;
    }

    unsigned int VBO1, VAO1,EBO1;
    glGenVertexArrays(1, &VAO1);
    glGenBuffers(1, &VBO1);
    glGenBuffers(1, &EBO1);


    glBindVertexArray(VAO1);

    glBindBuffer(GL_ARRAY_BUFFER, VBO1);
    glBufferData(GL_ARRAY_BUFFER, sizeof(float)*blue.size(), &blue[0], GL_STATIC_DRAW);

    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,EBO1);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER,sizeof(indices),indices,GL_STATIC_DRAW);


    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 6 * sizeof(float), (void*)0);
    glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 6 * sizeof(float), (void*)(3*sizeof(float)));
    glEnableVertexAttribArray(0);
    glEnableVertexAttribArray(1);

    glBindBuffer(GL_ARRAY_BUFFER,0);

    glBindVertexArray(0);

    unsigned int VBO2, VAO2,EBO2;
    glGenVertexArrays(1, &VAO2);
    glGenBuffers(1, &VBO2);
    glGenBuffers(1, &EBO2);


    glBindVertexArray(VAO2);

    glBindBuffer(GL_ARRAY_BUFFER, VBO2);
    glBufferData(GL_ARRAY_BUFFER, sizeof(float)*green.size(), &green[0], GL_STATIC_DRAW);

    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,EBO2);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER,sizeof(indices),indices,GL_STATIC_DRAW);


    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 6 * sizeof(float), (void*)0);
    glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 6 * sizeof(float), (void*)(3*sizeof(float)));
    glEnableVertexAttribArray(0);
    glEnableVertexAttribArray(1);

    glBindBuffer(GL_ARRAY_BUFFER,0);

    glBindVertexArray(0);

    unsigned int VBO3, VAO3,EBO3;
    glGenVertexArrays(1, &VAO3);
    glGenBuffers(1, &VBO3);
    glGenBuffers(1, &EBO3);


    glBindVertexArray(VAO3);

    glBindBuffer(GL_ARRAY_BUFFER, VBO3);
    glBufferData(GL_ARRAY_BUFFER, sizeof(float)*red.size(), &red[0], GL_STATIC_DRAW);

    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,EBO3);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER,sizeof(indices),indices,GL_STATIC_DRAW);


    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 6 * sizeof(float), (void*)0);
    glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 6 * sizeof(float), (void*)(3*sizeof(float)));
    glEnableVertexAttribArray(0);
    glEnableVertexAttribArray(1);

    glBindBuffer(GL_ARRAY_BUFFER,0);

    glBindVertexArray(0);





    // render loop
    // -----------

    while (!glfwWindowShouldClose(window))
    {
        // input
        // -----
        processInput(window);

        // render
        // ------
        glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        glUseProgram(shaderProgram);

        glBindVertexArray(VAO1);
        glDrawElements(GL_TRIANGLES,sizeof(indices),GL_UNSIGNED_INT,nullptr);

        glBindVertexArray(VAO2);
        glDrawElements(GL_TRIANGLES,sizeof(indices),GL_UNSIGNED_INT,nullptr);

        glBindVertexArray(VAO3);
        glDrawElements(GL_TRIANGLES,sizeof(indices),GL_UNSIGNED_INT,nullptr);

        // glfw: swap buffers and poll IO events (keys pressed/released, mouse moved etc.)
        // -------------------------------------------------------------------------------
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    // optional: de-allocate all resources once they've outlived their purpose:
    // ------------------------------------------------------------------------
    glDeleteVertexArrays(1, &VAO1);
    glDeleteBuffers(1, &VBO1);

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

