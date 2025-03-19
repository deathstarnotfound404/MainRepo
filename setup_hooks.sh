#!/bin/bash

# A repo gyökérmappájának lekérése
REPO_ROOT=$(git rev-parse --show-toplevel)

# Másoljuk a post-push hookot, ha még nem létezik
HOOK_PATH="$REPO_ROOT/.git/hooks/post-push"

if [ ! -f "$HOOK_PATH" ]; then
    echo "🔧 Hook telepítése..."
    cat > "$HOOK_PATH" <<EOL
#!/bin/bash
REPO_ROOT=\$(git rev-parse --show-toplevel)
"\$REPO_ROOT/create_pr.sh"
EOL
    chmod +x "$HOOK_PATH"
    echo "✅ Hook sikeresen telepítve!"
else
    echo "⚠️  A post-push hook már létezik, nincs szükség újratelepítésre."
fi
