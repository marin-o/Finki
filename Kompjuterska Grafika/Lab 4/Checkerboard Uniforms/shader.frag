#version 330 core
out vec4 FragColor;

in vec3 ourColor;
in vec2 TexCoord;

// texture sampler
uniform sampler2D texture1;

uniform float rows = 4.0f;
uniform float cols = 4.0f;

float checker(vec2 uv, float repeats_rows, float repeats_cols)
{
    float cx = floor(repeats_rows * uv.x);
    float cy = floor(repeats_cols * uv.y);
    float result = mod(cx + cy, 2.0);
    return sign(result);
}

void main (void)
{
    vec2 uv = TexCoord.xy;
    float c = mix(1.0, 0.0, checker(uv, rows, cols));
    FragColor = vec4(c, c, c, 1.0);
}

