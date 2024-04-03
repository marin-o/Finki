#version 330 core
out vec4 FragColor;

struct Material {
    sampler2D diffuse;
    sampler2D specular;
    float shininess;
};

struct Light {
    vec3 position;

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

in vec3 FragPos;
in vec3 Normal;
in vec2 TexCoords;

uniform vec3 viewPos;
uniform Material material;
uniform Light light;

uniform float time;

void main()
{
    // ambient
    vec3 ambient = light.ambient * texture(material.diffuse, TexCoords).rgb;

    // diffuse
    vec3 norm = normalize(Normal);
    vec3 lightDir = normalize(light.position - FragPos);
    float diff = max(dot(norm, lightDir), 0.0);
    vec3 diffuse = light.diffuse * diff * vec3(texture(material.diffuse, TexCoords));

    //check if fragment shader is on the wood
    float isAtWood = step(0.085, TexCoords.x) * step(TexCoords.x,0.925) * step(0.085, TexCoords.y) * step(TexCoords.y,0.925);

    // specular
    vec3 viewDir = normalize(viewPos - FragPos);
    vec3 reflectDir = reflect(-lightDir, norm);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    vec3 specular = light.specular * spec * vec3(texture(material.specular, TexCoords));

    vec3 result = ambient + diffuse + specular;

    //get texture sample
    vec3 textureSample = vec3(texture(material.diffuse, TexCoords));
    float textureBrightness = (cos(time)+1)/2.0f;

    //change texture brightness
    textureSample *= textureBrightness;

    //final result
    result = mix(result, textureSample, isAtWood);


    FragColor = vec4(result, 1.0);
}

