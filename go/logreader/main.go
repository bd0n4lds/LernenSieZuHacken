package main

import (
	"bufio"
	"flag"
	"io"
	"os"
	"strings"
)

func main() {

	var onlymatches bool
	flag.BoolVar(&onlymatches, "m", false, "only show matches from known hardcoded hashes")
	flag.Parse()

	// accept input piped to program, or from an arg
	var processes io.Reader
	processes = os.Stdin

	process := flag.Arg(0)

	if process != "" {
		processes = strings.NewReader(process)
	}

}

// readLines reads a whole file into memory
// and returns a slice of its lines.
func readLines(path string) ([]string, error) {
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	defer file.Close()

	var lines []string
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		lines = append(lines, scanner.Text())
	}
	return lines, scanner.Err()
}

func contains(slice []string, item string) bool {
	set := make(map[string]struct{}, len(slice))
	for _, s := range slice {
		set[s] = struct{}{}
	}

	_, ok := set[item]
	return ok
}
