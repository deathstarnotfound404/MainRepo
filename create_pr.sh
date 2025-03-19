#!/bin/bash

# 🛠️ Repo adatok (EZEKET ÁLLÍTSD BE!)
REPO_OWNER="myorg"   # <-- Cseréld le a GitHub usered vagy szervezeted nevére
REPO_NAME="myproject"  # <-- Cseréld le a repo nevére
BASE_BRANCH="skeleton" # <-- A branch, amelybe merge-ölni szeretnél (pl. "skeleton")

# 📌 Jelenlegi branch lekérése
CURRENT_BRANCH=$(git rev-parse --abbrev-ref HEAD)

# 🛑 Ha a jelenlegi branch megegyezik a BASE_BRANCH-csel, ne hozzunk létre PR-t
if [ "$CURRENT_BRANCH" == "$BASE_BRANCH" ]; then
    echo "⚠️  A(z) '$BASE_BRANCH' branchen vagy, nincs szükség PR-re."
    exit 1
fi

# 🔼 Push az új branch-re
git push --set-upstream origin "$CURRENT_BRANCH"

# 🔗 PR link generálása
PR_URL="https://github.com/$REPO_OWNER/$REPO_NAME/compare/$BASE_BRANCH...$CURRENT_BRANCH?expand=1"

# 🌍 PR link kiírása és megnyitása böngészőben
echo "🔗 Pull request létrehozása: $PR_URL"
xdg-open "$PR_URL" 2>/dev/null || open "$PR_URL"  # Linux/Mac támogatás