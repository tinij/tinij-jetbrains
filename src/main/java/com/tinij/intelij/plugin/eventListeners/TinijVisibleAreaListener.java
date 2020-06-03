package com.tinij.intelij.plugin.eventListeners;

import com.intellij.openapi.editor.event.VisibleAreaEvent;
import com.intellij.openapi.editor.event.VisibleAreaListener;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.tinij.intelij.plugin.Tinij;

public class TinijVisibleAreaListener  implements VisibleAreaListener {

    private Tinij tinij;

    public TinijVisibleAreaListener(Tinij tinij) {
        this.tinij = tinij;
    }


    @Override
    public void visibleAreaChanged(VisibleAreaEvent visibleAreaEvent) {
        FileDocumentManager instance = FileDocumentManager.getInstance();
        VirtualFile file = instance.getFile(visibleAreaEvent.getEditor().getDocument());
        Project project = visibleAreaEvent.getEditor().getProject();
        this.tinij.addActivity(file, project, false);
    }

}
