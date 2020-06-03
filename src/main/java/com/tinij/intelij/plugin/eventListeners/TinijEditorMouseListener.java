package com.tinij.intelij.plugin.eventListeners;

import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.event.EditorMouseListener;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.tinij.intelij.plugin.Tinij;

public class TinijEditorMouseListener implements EditorMouseListener {

    private Tinij tinij;

    public TinijEditorMouseListener(Tinij tinij) {
        this.tinij = tinij;
    }

    @Override
    public void mousePressed(EditorMouseEvent editorMouseEvent) {
        FileDocumentManager instance = FileDocumentManager.getInstance();
        VirtualFile file = instance.getFile(editorMouseEvent.getEditor().getDocument());
        Project project = editorMouseEvent.getEditor().getProject();
        this.tinij.addActivity(file, project, false);

    }

    @Override
    public void mouseClicked(EditorMouseEvent editorMouseEvent) {
    }

    @Override
    public void mouseReleased(EditorMouseEvent editorMouseEvent) {
    }

    @Override
    public void mouseEntered(EditorMouseEvent editorMouseEvent) {
    }

    @Override
    public void mouseExited(EditorMouseEvent editorMouseEvent) {
    }
}
