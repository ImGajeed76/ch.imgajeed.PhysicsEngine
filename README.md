# PhysicsEngine

A 3D wireframe rendering engine built from scratch in Kotlin using Java Swing.

> **Note:** This was one of my first projects, created in March 2021 when I was 13. The code remains unchanged from the original - this README was added later for archival purposes.

## What it does

- Renders 3D cubes as wireframes
- First-person camera with WASD + mouse look
- Custom matrix math library for 3D transformations
- Perspective projection (world space → screen space)

## Controls

| Key | Action |
|-----|--------|
| W/S | Move forward/backward |
| A/D | Strafe left/right |
| Space | Move up |
| Shift | Move down |
| Mouse | Look around |
| ESC | Release mouse |

## How it works

The engine implements the 3D graphics pipeline manually:

1. **Matrix.kt** - Generic matrix class with multiplication
2. **Math3d.kt** - 4x4 transformation matrices (translation, rotation X/Y/Z, perspective projection)
3. **Cube.kt** - 8 vertices defined in 3D, projected to 2D for rendering
4. **Camera.kt** - Position, rotation, and input handling
5. **Window.kt** - Java Swing rendering loop

No OpenGL, no game engine - just `Graphics.drawLine()` and math.

## Running

Requires Kotlin and Java. Built with Gradle.

```bash
./gradlew run
```

## Author

Oliver Seifert ([@ImGajeed76](https://github.com/ImGajeed76)) - March 2021
