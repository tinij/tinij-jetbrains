package com.tinij.intelij.plugin.eventListeners;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileDocumentManagerListener;
import com.intellij.openapi.vfs.VirtualFile;
import com.tinij.intelij.plugin.Tinij;
import org.jetbrains.annotations.NotNull;

public class TinijSaveListener implements FileDocumentManagerListener {

    private Tinij tinij;

    public TinijSaveListener(Tinij tinij) {
        this.tinij = tinij;
    }


    @Override
    public void beforeDocumentSaving(Document document) {
        FileDocumentManager instance = FileDocumentManager.getInstance();
        VirtualFile file = instance.getFile(document);
        this.tinij.addActivity(file, document, true);
    }

    @Override
    public void beforeAllDocumentsSaving() {
    }

    @Override
    public void beforeFileContentReload(@NotNull VirtualFile file, @NotNull Document document) {
    }

    @Override
    public void fileWithNoDocumentChanged(@NotNull VirtualFile file) {
    }

    @Override
    public void fileContentReloaded(@NotNull VirtualFile file, @NotNull Document document) {
    }

    @Override
    public void fileContentLoaded(@NotNull VirtualFile file, @NotNull Document document) {
    }

    @Override
    public void unsavedDocumentsDropped() {
    }
}
