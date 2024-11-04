package cz.johnyapps.eddiehostopky.migration.data

import cz.johnyapps.eddiehostopky.migration.data.model.MigrationInfoEntity
import cz.johnyapps.eddiehostopky.migration.domain.MigrationRepository

class RoomMigrationRepository(
    private val migrationDao: MigrationDao,
) : MigrationRepository {

    override suspend fun getMigratedToVersion(): Int {
        val info = migrationDao.getMigrationInfo()
        return info?.migratedToVersion ?: 0
    }

    override suspend fun setMigratedToVersion(version: Int) {
        val info = MigrationInfoEntity(
            id = 0,
            migratedToVersion = version
        )
        migrationDao.insertInfoMigration(info)
    }
}
