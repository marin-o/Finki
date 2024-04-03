#include <glad/glad.h>
#include <GLFW/glfw3.h>

#include <iostream>

void framebuffer_size_callback(GLFWwindow* window, int width, int height);
void processInput(GLFWwindow *window);

// settings
const unsigned int SCR_WIDTH = 800;
const unsigned int SCR_HEIGHT = 600;

const char *vertexShaderSource = "#version 330 core\n"
                                 "layout (location = 0) in vec3 aPos;\n"
                                 "void main()\n"
                                 "{\n"
                                 "   gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);\n"
                                 "}\0";
const char *fragmentShader1Source = "#version 330 core\n"
                                    "out vec4 FragColor;\n"
                                    "void main()\n"
                                    "{\n"
                                    "   FragColor = vec4(1.0f, 0.5f, 0.2f, 1.0f);\n"
                                    "}\n\0";
const char *fragmentShader2Source = "#version 330 core\n"
                                    "out vec4 FragColor;\n"
                                    "void main()\n"
                                    "{\n"
                                    "   FragColor = vec4(1.0f, 1.0f, 0.0f, 1.0f);\n"
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
    GLuint shaderProgram = glCreateProgram();
    GLuint fragmentShaderOrange = glCreateShader(GL_FRAGMENT_SHADER);
    GLuint fragmentShaderYellow = glCreateShader(GL_FRAGMENT_SHADER);
    GLuint shaderProgramOrange = glCreateProgram();
    GLuint shaderProgramYellow = glCreateProgram();
    glShaderSource(vertexShader,1,&vertexShaderSource,nullptr);
    glCompileShader(vertexShader);
    glShaderSource(fragmentShaderOrange,1,&fragmentShader1Source, nullptr);
    glCompileShader(fragmentShaderOrange);
    glShaderSource(fragmentShaderYellow,1,&fragmentShader2Source, nullptr);
    glCompileShader(fragmentShaderYellow);

    glAttachShader(shaderProgramOrange,vertexShader);
    glAttachShader(shaderProgramOrange,fragmentShaderOrange);
    glLinkProgram(shaderProgramOrange);

    glAttachShader(shaderProgramYellow,vertexShader);
    glAttachShader(shaderProgramYellow,fragmentShaderYellow);
    glLinkProgram(shaderProgramYellow);

    glGetShaderiv(fragmentShaderOrange,GL_COMPILE_STATUS,&success);
    if(!success){
        glGetShaderInfoLog(vertexShader, 512, nullptr, infoLog);
        std::cout << "ERROR::SHADER::VERTEX::COMPILATION_FAILED\n" << infoLog << std::endl;
    }

    glGetShaderiv(fragmentShaderYellow,GL_COMPILE_STATUS,&success);
    if(!success){
        glGetShaderInfoLog(vertexShader, 512, nullptr, infoLog);
        std::cout << "ERROR::SHADER::VERTEX::COMPILATION_FAILED\n" << infoLog << std::endl;
    }


    std::cout<<"Se nadevam deka ke ja prifatite i vaka, ja napraviv sledniot den po chasot "
             <<"vo koj ja najavivte vezbata, odnosno toa bi bilo denot po vtorite auditoriski vezhbi.";
    // set up vertex data (and buffer(s)) and configure vertex attributes
    // ------------------------------------------------------------------
    float verticesFace[] = {
            //roof vertices
            -0.6f,  0.575f,  0.0f, //top center center-roof
            -0.975f, -0.175f,  0.0f,//bottom left center
            -0.225f, -0.175f,  0.0f, //bottom right center
            //face vertices
            -0.225f,-0.8f,0.0f,//bottom right face
            -0.975f,-0.8f,0.0f//bottom left face
    };
    unsigned int indicesFace[] = {
            0,1,2, //center-roof
            1,2,3,  //bottom face right triangle
            1,3,4  //bottom face left triangle
    };
    float verticesSide[] = {
            //roof vertices
            -0.225f, -0.175f,  0.0f,  // bottom left side roof and face
            0.35f, 0.675f, 0.0f,  // top right side roof
            -0.6f,  0.575f,  0.0f,  // top left side roof
            0.65f,0.0f,0.0f, //bottom right side roof
            //face vertices
            -0.225f,-0.8f,0.0f, //bottom right side face
            0.65f,-0.7,0.0f //bottom right side face
    };

    unsigned int indicesSide[] = {
            0,1,2, //top triangle roofside
            0,1,3, //bottom triangle roofside
            0,4,5, //bottom side face triangle
            0,3,5 //top side face triangle
    };



    unsigned int EBOs[2];
    glGenBuffers(2,EBOs);

    unsigned int VBOs[2], VAOs[2];
    glGenVertexArrays(2, VAOs);
    glGenBuffers(2, VBOs);
    // bind the Vertex Array Object first, then bind and set vertex buffer(s), and then configure vertex attributes(s).
    glBindVertexArray(VAOs[0]);

    glBindBuffer(GL_ARRAY_BUFFER, VBOs[0]);
    glBufferData(GL_ARRAY_BUFFER, sizeof(verticesFace), verticesFace, GL_STATIC_DRAW);

    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,EBOs[0]);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(indicesFace), indicesFace, GL_STATIC_DRAW);
    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(float), nullptr);
    glEnableVertexAttribArray(0);

    // note that this is allowed, the call to glVertexAttribPointer registered VBO as the vertex attribute's
    // bound vertex buffer object so afterwards we can safely unbind
    //glBindBuffer(GL_ARRAY_BUFFER, 0);

    // You can unbind the VAO afterwards so other VAO calls won't accidentally modify this VAO,
    // but this rarely happens. Modifying other VAOs requires a call to glBindVertexArray anyway
    // so we generally don't unbind VAOs (nor VBOs) when it's not directly necessary.
    //glBindVertexArray(0);

    //unsigned int VBO2,VAO2;
    /*glGenVertexArrays(1,&VAO2);
    glGenBuffers(1,&VBO2);
*/
    glBindVertexArray(VAOs[1]);

    glBindBuffer(GL_ARRAY_BUFFER,VBOs[1]);
    glBufferData(GL_ARRAY_BUFFER, sizeof(verticesSide), verticesSide, GL_STATIC_DRAW);
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,EBOs[1]);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(indicesSide), indicesSide, GL_STATIC_DRAW);
    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(float), nullptr);
    glEnableVertexAttribArray(0);

    glBindBuffer(GL_ARRAY_BUFFER,0);
    glBindVertexArray(0);


    // uncomment this call to draw in wireframe polygons.
    //glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

    // render loop
    // -----------

    glPointSize(5.0f);
    while (!glfwWindowShouldClose(window))
    {
        // input
        // -----
        processInput(window);

        // render
        // ------
        glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);
        glUseProgram(shaderProgramYellow);
        // draw our first triangle


        // seeing as we only have a single VAO there's no need to bind it every time,
        // but we'll do so to keep things a bit more organized
        glBindVertexArray(VAOs[0]);
        glDrawElements(GL_TRIANGLES, 9, GL_UNSIGNED_INT,0);

        glUseProgram(shaderProgramOrange);
        glBindVertexArray(VAOs[1]);
        glDrawElements(GL_TRIANGLES,12,GL_UNSIGNED_INT,0);
        // glBindVertexArray(0); // no need to unbind it every time

        // glfw: swap buffers and poll IO events (keys pressed/released, mouse moved etc.)
        // -------------------------------------------------------------------------------
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    // optional: de-allocate all resources once they've outlived their purpose:
    // ------------------------------------------------------------------------
    glDeleteVertexArrays(2,VAOs);
    glDeleteBuffers(2,VBOs);

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

