# Vertical Line Counter

A Java console application that counts the number of vertical black lines in a black-and-white JPG image.

## Features

- Counts distinct vertical black lines in a JPG image
- Accepts relative or absolute image paths
- Handles invalid input

## Compilation

Compile the application from the project root:

```bash
javac src/Main.java
```

## Usage

Run the application from the project root:

```bash
java -cp src Main <image_path>
```

### Examples

Using a relative path:

```bash
java -cp src Main test-images/img_1.jpg
```

Using an absolute path:

```bash
java -cp src Main "/Users/yourname/toyota-vertical-line-counter/test-images/img_1.jpg"
```

## Output

The program prints a single integer representing the number of vertical black lines.

Example:

```text
1
```

## Complexity

- Time Complexity: O(width × height)
- Space Complexity: O(1)

## Expected Results

| Image | Expected Output |
| img_1.jpg | 1 |
| img_2.jpg | 3 |
| img_3.jpg | 1 |
| img_4.jpg | 7 |

## Error Handling

The application validates user input and handles common error scenarios.

The following cases are supported:

- Incorrect number of command-line arguments
- Image file does not exist
- Invalid or unreadable image file
- I/O errors while loading the image
- Unexpected runtime exceptions

Instead of crashing, the application prints an informative error message and exits cleanly.
