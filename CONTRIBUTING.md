# Contributing Guidelines 
## General Workflow
Part 1: Pull the current code to your fork of the repository by fetching upstream <br>
Part 2: Make a commit to your fork of the repository <br>
Part 3: Create a pull request contribution <br>
Part 4: Request review from another group member 

## Specifics
### Github Desktop
- Fork the repo and clone it to your local computer
- Click on the fetch origin button to pull the latest version of your repo
- Use the commit feature to upload the code to your fork
- Fetch the upstream and then open a pull request from the web browser
- Check pending issues and pull requests from the web browser

### Git Bash Command Line Interface
Commit code from branch
```
git checkout branch
git add .
git commit -m "Name of commit"
git push origin main
```

Fetch upstream from branch
```
git checkout branch
git merge main
git push 
```

OR

```
git reset --hard main
git push
``` 

## Merging Upstream from Branch
Since it's not possible to fork a repo to itself, run:
```
git checkout branch
git fetch origin
git merge origin/main
git push
```

## Merge Commits
If able to fix using Github web browser, open file and resolve manually. 
If unable to fix, use the command line to force a `git merge --no-ff` and then a rebase operation. 
Alternatively, close the pull request and use the Git Bash CLI to `--force` reroute the commit HEAD to another pull request/branch, and link that pull request to the previous commit(s). 
Open an issue if required, with the `bugs` label. 

## Troubleshooting
If an error occurs with a commit: <br>
1. Make a local copy of the current version of the repository (make sure nothing from this is corrupted by merge conflicts)
2. Delete the repository from your local computer
3. Reimport the respoitory `git clone https://github.com/Bobliuuu/ImageManipulationProject.git`
4. Reset the HEAD commit and make the required changes
