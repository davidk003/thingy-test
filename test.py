from playwright.sync_api import sync_playwright

   def print_request_as_curl(request):
       # Get the request method (e.g., GET, POST)
       method = request.method
       # Start building the curl command with the method
       curl_cmd = f"curl -X {method}"

       # Add all the headers using the -H flag
       for header_name, header_value in request.headers.items():
           curl_cmd += f" -H \"{header_name}: {header_value}\""

       # If the request includes POST data (or any body), add it as data
       if request.post_data:
           # Escape single quotes in the post data so that the shell command works correctly
           data = request.post_data.replace("'", "'\\''")
           curl_cmd += f" --data '{data}'"

       # Append the final URL part
       curl_cmd += f" \"{request.url}\""

       # Print the complete curl command
       print(curl_cmd)

   def main():
       with sync_playwright() as playwright:
           # Launch the browser in headless mode
           browser = playwright.chromium.launch(headless=True)
           page = browser.new_page()

           # Attach an event listener to catch every network request
           page.on("request", lambda request: print_request_as_curl(request))

           # Define the target URL (replace with your actual target)
           target_url = "https://podcast.ucsd.edu/#courses"
           page.goto(target_url, wait_until="networkidle")

           # Optional: wait a little longer to catch any background requests
           page.wait_for_timeout(5000)

           browser.close()

   if __name__ == "__main__":
       main()
