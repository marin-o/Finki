#version 330 core
out vec4 FragColor;

in vec3 ourColor;
in vec2 TexCoord;

// texture sampler
uniform sampler2D texture1;

float checker(vec2 uv, float repeats)
{
    float cx = floor(repeats * uv.x);
    float cy = floor(repeats * uv.y);
    float result = mod(cx + cy, 2.0);
    return sign(result);
}

void main (void)
{
    vec2 uv = TexCoord.xy;
    float c = mix(1.0, 0.0, checker(uv, 4.0));
    FragColor = vec4(c, c, c, 1.0);
}

