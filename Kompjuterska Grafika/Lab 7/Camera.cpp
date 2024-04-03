#include <Camera.hpp>
#include <iostream>

bool isFalling = false;
bool jumpHeld = false;
bool crouchHeld = false;
bool isJumping = false;
bool isCrouching = false;

Camera::Camera(glm::vec3 position, glm::vec3 up, float yaw, float pitch)
        : Front(glm::vec3(0.0f, 0.0f, -1.0f)),
        MovementSpeed(SPEED), JumpForce(JUMP_FORCE), CrouchSpeed(CROUCH_SPEED),
          MouseSensitivity(SENSITIVITY), Zoom(ZOOM) {
    Position = position;
    WorldUp = up;
    Yaw = yaw;
    Pitch = pitch;
    updateCameraVectors();
}
// Constructor with scalar values
Camera::Camera(float posX, float posY, float posZ, float upX, float upY,
               float upZ, float yaw, float pitch)
        : Front(glm::vec3(0.0f, 0.0f, -1.0f)), MovementSpeed(SPEED),
          MouseSensitivity(SENSITIVITY), Zoom(ZOOM) {
    Position = glm::vec3(posX, posY, posZ);
    WorldUp = glm::vec3(upX, upY, upZ);
    Yaw = yaw;
    Pitch = pitch;
    updateCameraVectors();
}

// Returns the view matrix calculated using Euler Angles and the LookAt Matrix
glm::mat4 Camera::GetViewMatrix() {
    return glm::lookAt(Position, Position + Front, Up);
}

// Processes action received from any keyboard-like action system. Accepts action
// parameter in the form of camera defined ENUM (to abstract it from windowing
// systems)
void Camera::ProcessKeyboard(Camera_Actions action, float deltaTime) {
    float velocity = MovementSpeed * deltaTime;
    float jump = JumpForce * deltaTime;
    float crouch = CrouchSpeed * deltaTime;
    if (action == FORWARD)
        Position += Front * velocity;
    if (action == BACKWARD)
        Position -= Front * velocity;
    if (action == LEFT)
        Position -= Right * velocity;
    if (action == RIGHT)
        Position += Right * velocity;
    if (action == JUMP)
        Jump(jump);
    if (action == CROUCH)
        Crouch(crouch);
}

void Camera::Jump(float jump){
    if (!isJumping && !isFalling) {
        Position += Up * jump;
        isJumping = true;
    }
    else if(jumpHeld && !isFalling){
        Position += Up * jump;
        if(Position.y >= JUMP_HEIGHT)
            isFalling = true;
    }
}

void Camera::Crouch(float crouch){
    if (crouchHeld) {
        isCrouching = true;
        if (Position.y > CROUCH_DEPTH){
            Position -= Up * crouch;
        }
    }
    else if(Position.y < MIN_HEIGHT){
            Position += Up * crouch;
    }
}

void Camera::JumpState(bool jumpButtonState) {
    jumpHeld = jumpButtonState;
    if (!jumpButtonState)
        isFalling = true;
    if(Position.y <= 0)
        isJumping = false;
}

void Camera::CrouchState(bool crouchButtonState) {
    /*if(!crouchButtonState){
        isCrouching = false;
    }
    else*/
    if(crouchButtonState) {
        crouchHeld = true;
    }
    else crouchHeld = false;
}

void Camera::Gravity(float deltaTime) {
    if (Position.y > MIN_HEIGHT) {
        Position -= GRAVITY_STRENGTH*Up * deltaTime;
    }
    else isFalling = false;
}

// Processes input received from a mouse input system. Expects the offset
// value in both the x and y direction.
void Camera::ProcessMouseMovement(float xoffset, float yoffset,
                                  GLboolean constrainPitch) {
    xoffset *= MouseSensitivity;
    yoffset *= MouseSensitivity;

    Yaw += xoffset;
    Pitch += yoffset;

    // Make sure that when pitch is out of bounds, screen doesn't get flipped
    if (constrainPitch) {
        if (Pitch > 89.0f)
            Pitch = 89.0f;
        if (Pitch < -89.0f)
            Pitch = -89.0f;
    }

    // Update Front, Right and Up Vectors using the updated Euler angles
    updateCameraVectors();
}

// Processes input received from a mouse scroll-wheel event. Only requires
// input on the vertical wheel-axis
void Camera::ProcessMouseScroll(float yoffset) {
    if (Zoom >= 1.0f && Zoom <= 45.0f)
        Zoom -= yoffset;
    if (Zoom <= 1.0f)
        Zoom = 1.0f;
    if (Zoom >= 45.0f)
        Zoom = 45.0f;
}

// Calculates the front vector from the Camera's (updated) Euler Angles
void Camera::updateCameraVectors() {
    // Calculate the new Front vector
    glm::vec3 front;
    front.x = static_cast<float>(cos(glm::radians(static_cast<double>(Yaw))) *
                                 cos(glm::radians(static_cast<double>(Pitch))));
    front.y = static_cast<float>(sin(glm::radians(static_cast<double>(Pitch))));
    front.z = static_cast<float>(sin(glm::radians(static_cast<double>(Yaw))) *
                                 cos(glm::radians(static_cast<double>(Pitch))));
    Front = glm::normalize(front);
    // Also re-calculate the Right and Up vector
    Right = glm::normalize(glm::cross(
            Front, WorldUp)); // Normalize the vectors, because their length gets
    // closer to 0 the more you look up or down which
    // results in slower movement.
    Up = glm::normalize(glm::cross(Right, Front));
}
